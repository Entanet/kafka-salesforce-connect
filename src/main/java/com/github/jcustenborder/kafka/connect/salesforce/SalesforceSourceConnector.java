/**
 * Copyright © 2016 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.kafka.connect.salesforce;

import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.github.jcustenborder.kafka.connect.utils.config.Description;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.github.jcustenborder.kafka.connect.salesforce.rest.SalesforceRestClient;
import com.github.jcustenborder.kafka.connect.salesforce.rest.SalesforceRestClientFactory;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.ApiVersion;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.PushTopic;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectDescriptor;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectMetadata;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectsResponse;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Description("The SalesforceSourceConnector is used to read changes from Salesforce in realtime.")
public class SalesforceSourceConnector extends SourceConnector {
  private static Logger log = LoggerFactory.getLogger(SalesforceSourceConnector.class);
  List<Map<String, String>> configs = new ArrayList<>();
  private SalesforceSourceConnectorConfig config;

  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }

  @Override
  public void start(Map<String, String> map) {
    config = new SalesforceSourceConnectorConfig(map);

    SalesforceRestClient client = SalesforceRestClientFactory.create(this.config);
    client.authenticate();

    List<ApiVersion> apiVersions = client.apiVersions();
    ApiVersion apiVersion = apiVersions.get(0);
    client.apiVersion(apiVersion);

    SObjectsResponse sObjectsResponse = client.objects();
    SObjectMetadata sObjectMetadata = null;
    SObjectDescriptor sObjectDescriptor = null;

    for (SObjectMetadata metadata : sObjectsResponse.sobjects()) {
      if (this.config.salesForceObject.equalsIgnoreCase(metadata.name())) {
        sObjectMetadata = metadata;
        sObjectDescriptor = client.describe(metadata);
        break;
      }
    }

    Preconditions.checkNotNull(sObjectMetadata, "Could not find metadata for object '%s'", this.config.salesForceObject);
    Preconditions.checkNotNull(sObjectDescriptor, "Could not find descriptor for object '%s'", this.config.salesForceObject);

    List<PushTopic> pushTopics = client.pushTopics();
    PushTopic pushTopic = null;

    for (PushTopic p : pushTopics) {
      if (this.config.salesForcePushTopicName.equals(p.name())) {
        pushTopic = p;
        break;
      }
    }

    if (null == pushTopic && this.config.salesForcePushTopicCreate) {
      if (log.isWarnEnabled()) {
        log.warn("PushTopic {} was not found.", this.config.salesForcePushTopicName);
      }

      pushTopic = new PushTopic();
      pushTopic.name(this.config.salesForcePushTopicName);

      Set<String> fields = new LinkedHashSet<>();
      for (SObjectDescriptor.Field f : sObjectDescriptor.fields()) {
        if (SObjectHelper.isTextArea(f)) {
          continue;
        }
        fields.add(f.name());
      }

      String query = String.format(
          "SELECT %s FROM %s",
          Joiner.on(',').join(fields),
          sObjectDescriptor.name()
      );
      pushTopic.query(query);
      if (log.isInfoEnabled()) {
        log.info("Setting query for {} to \n{}", pushTopic.name(), pushTopic.query());
      }
      pushTopic.notifyForOperationCreate(this.config.salesForcePushTopicNotifyCreate);
      pushTopic.notifyForOperationUpdate(this.config.salesForcePushTopicNotifyUpdate);
      pushTopic.notifyForOperationDelete(this.config.salesForcePushTopicNotifyDelete);
      pushTopic.notifyForOperationUndelete(this.config.salesForcePushTopicNotifyUndelete);
      pushTopic.apiVersion(new BigDecimal(apiVersion.version()));

      if (log.isInfoEnabled()) {
        log.info("Creating PushTopic {}", pushTopic.name());
      }

      client.pushTopic(pushTopic);
    }

    Preconditions.checkNotNull(pushTopic, "PushTopic '%s' was not found.", this.config.salesForcePushTopicName);

    Map<String, String> taskSettings = new HashMap<>();
    taskSettings.putAll(map);
    taskSettings.put(SalesforceSourceConnectorConfig.VERSION_CONF, apiVersion.version());
    this.configs.add(taskSettings);
  }

  @Override
  public Class<? extends Task> taskClass() {
    return SalesforceSourceTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int i) {
    return this.configs;
  }

  @Override
  public void stop() {


  }

  @Override
  public ConfigDef config() {
    return SalesforceSourceConnectorConfig.conf();
  }
}