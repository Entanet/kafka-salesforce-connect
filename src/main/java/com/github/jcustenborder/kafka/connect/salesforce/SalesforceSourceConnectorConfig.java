/**
 * Copyright © 2016 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jcustenborder.kafka.connect.salesforce;

import com.github.jcustenborder.kafka.connect.utils.config.ValidPattern;
import com.github.jcustenborder.kafka.connect.utils.template.StructTemplate;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;


public class SalesforceSourceConnectorConfig extends AbstractConfig {

  public static final String USERNAME_CONF = "salesforce.username";
  public static final String PASSWORD_CONF = "salesforce.password";
  public static final String PASSWORD_TOKEN_CONF = "salesforce.password.token";
  public static final String CONSUMER_KEY_CONF = "salesforce.consumer.key";
  public static final String CONSUMER_SECRET_CONF = "salesforce.consumer.secret";
  public static final String INSTANCE_CONF = "salesforce.instance";
  public static final String CURL_LOGGING_CONF = "curl.logging";
  public static final String SALESFORCE_PUSH_TOPIC_NAME_CONF = "salesforce.push.topic.name";
  public static final String SALESFORCE_PUSH_TOPIC_CREATE_CONF = "salesforce.push.topic.create";
  public static final String SALESFORCE_PUSH_TOPIC_NOTIFY_CREATE_CONF = "salesforce.push.topic.notify.create";
  public static final String SALESFORCE_PUSH_TOPIC_NOTIFY_UPDATE_CONF = "salesforce.push.topic.notify.update";
  public static final String SALESFORCE_PUSH_TOPIC_NOTIFY_DELETE_CONF = "salesforce.push.topic.notify.delete";
  public static final String SALESFORCE_PUSH_TOPIC_NOTIFY_UNDELETE_CONF = "salesforce.push.topic.notify.undelete";
  public static final String VERSION_CONF = "salesforce.version";

  public static final String SALESFORCE_OBJECT_CONF = "salesforce.object";
  public static final String KAFKA_TOPIC_LOWERCASE_CONF = "kafka.topic.lowercase";
  public static final String KAFKA_TOPIC_CONF = "kafka.topic";

  public static final String CONNECTION_TIMEOUT_CONF = "connection.timeout";

  static final String VERSION_DOC = "The version of the salesforce API to use.";
  static final String USERNAME_DOC = "Salesforce username to connect with.";
  static final String PASSWORD_DOC = "Salesforce password to connect with.";
  static final String PASSWORD_TOKEN_DOC = "The Salesforce security token associated with the username.";
  static final String CONSUMER_KEY_DOC = "The consumer key for the OAuth application.";
  static final String CONSUMER_SECRET_DOC = "The consumer secret for the OAuth application.";
  static final String INSTANCE_DOC = "The Salesforce instance to connect to.";
  static final String CURL_LOGGING_DOC = "If enabled the logs will output the equivalent curl commands. This is a security risk because your authorization header will end up in the log file. Use at your own risk.";
  static final String CONNECTION_TIMEOUT_DOC = "The amount of time to wait while connecting to the Salesforce streaming endpoint.";
  static final String SALESFORCE_PUSH_TOPIC_NAME_DOC = "The Salesforce topic to subscribe to. If " + SALESFORCE_PUSH_TOPIC_CREATE_CONF +
      " is set to true, a PushTopic with this name will be created.";
  static final String SALESFORCE_PUSH_TOPIC_CREATE_DOC = "Flag to determine if the PushTopic should be created if it does not exist.";
  static final String SALESFORCE_PUSH_TOPIC_NOTIFY_CREATE_DOC = "Flag to determine if the PushTopic should respond to creates.";
  static final String SALESFORCE_PUSH_TOPIC_NOTIFY_UPDATE_DOC = "Flag to determine if the PushTopic should respond to updates.";
  static final String SALESFORCE_PUSH_TOPIC_NOTIFY_DELETE_DOC = "Flag to determine if the PushTopic should respond to deletes.";
  static final String SALESFORCE_PUSH_TOPIC_NOTIFY_UNDELETE_DOC = "Flag to determine if the PushTopic should respond to undeletes.";
  static final String SALESFORCE_OBJECT_DOC = "The Salesforce object to create a topic for.";
  static final String KAFKA_TOPIC_DOC = "The Kafka topic to write the SalesForce data to. This is a template driven by the " +
      "data returned by Salesforce. Any field in the schema can be used but you should always pick a value that is guarenteed to be there. " +
      "`" + SObjectHelper.FIELD_EVENT_TYPE + "` and `" + SObjectHelper.FIELD_OBJECT_TYPE + "` are two metadata fields that " +
      "are included on every record. For example you could put update and deletes in a different topic by using `salesforce.${_ObjectType}.${_EventType}`";
  static final String KAFKA_TOPIC_LOWERCASE_DOC = "Flag to determine if the kafka topic should be lowercased.";

  public SalesforceSourceConnectorConfig(Map<String, ?> parsedConfig) {
    super(conf(), parsedConfig);
    this.username = this.getString(USERNAME_CONF);
    this.password = this.getPassword(PASSWORD_CONF).value();
    this.passwordToken = this.getPassword(PASSWORD_TOKEN_CONF).value();
    this.consumerKey = this.getString(CONSUMER_KEY_CONF);
    this.consumerSecret = this.getPassword(CONSUMER_SECRET_CONF).value();
    this.instance = this.getString(INSTANCE_CONF);
    this.curlLogging = this.getBoolean(CURL_LOGGING_CONF);
    this.salesForcePushTopicName = this.getString(SALESFORCE_PUSH_TOPIC_NAME_CONF);
    this.salesForcePushTopicCreate = this.getBoolean(SALESFORCE_PUSH_TOPIC_CREATE_CONF);
    this.salesForceObject = this.getString(SALESFORCE_OBJECT_CONF);
    this.connectTimeout = this.getLong(CONNECTION_TIMEOUT_CONF);
    this.salesForcePushTopicNotifyCreate = this.getBoolean(SALESFORCE_PUSH_TOPIC_NOTIFY_CREATE_CONF);
    this.salesForcePushTopicNotifyUpdate = this.getBoolean(SALESFORCE_PUSH_TOPIC_NOTIFY_UPDATE_CONF);
    this.salesForcePushTopicNotifyDelete = this.getBoolean(SALESFORCE_PUSH_TOPIC_NOTIFY_DELETE_CONF);
    this.salesForcePushTopicNotifyUndelete = this.getBoolean(SALESFORCE_PUSH_TOPIC_NOTIFY_UNDELETE_CONF);
    this.version = this.getString(VERSION_CONF);
    this.kafkaTopicLowerCase = getBoolean(KAFKA_TOPIC_LOWERCASE_CONF);
    final String kafkaTopic = this.getString(KAFKA_TOPIC_CONF);

    StructTemplate template = new StructTemplate();
    template.addTemplate(TEMPLATE_NAME, kafkaTopic);
    this.kafkaTopicTemplate = template;
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        .define(USERNAME_CONF, Type.STRING, Importance.HIGH, USERNAME_DOC)
        .define(PASSWORD_CONF, Type.PASSWORD, Importance.HIGH, PASSWORD_DOC)
        .define(PASSWORD_TOKEN_CONF, Type.PASSWORD, Importance.HIGH, PASSWORD_TOKEN_DOC)
        .define(CONSUMER_KEY_CONF, Type.STRING, Importance.HIGH, CONSUMER_KEY_DOC)
        .define(CONSUMER_SECRET_CONF, Type.PASSWORD, Importance.HIGH, CONSUMER_SECRET_DOC)
        .define(INSTANCE_CONF, Type.STRING, "", Importance.HIGH, INSTANCE_DOC)
        .define(CURL_LOGGING_CONF, Type.BOOLEAN, false, Importance.LOW, CURL_LOGGING_DOC)
        .define(SALESFORCE_OBJECT_CONF, Type.STRING, Importance.HIGH, SALESFORCE_OBJECT_DOC)
        .define(KAFKA_TOPIC_CONF, Type.STRING, Importance.HIGH, KAFKA_TOPIC_DOC)
        .define(KAFKA_TOPIC_LOWERCASE_CONF, Type.BOOLEAN, true, Importance.HIGH, KAFKA_TOPIC_LOWERCASE_DOC)
        .define(CONNECTION_TIMEOUT_CONF, Type.LONG, 30000L, ConfigDef.Range.between(5 * 1000L, 600 * 1000L), Importance.LOW, CONNECTION_TIMEOUT_DOC)
        .define(VERSION_CONF, Type.STRING, "latest", ValidPattern.of("^(latest|[\\d\\.]+)$"), Importance.LOW, VERSION_DOC)
        .define(SALESFORCE_PUSH_TOPIC_NAME_CONF, Type.STRING, Importance.HIGH, SALESFORCE_PUSH_TOPIC_NAME_DOC)
        .define(SALESFORCE_PUSH_TOPIC_CREATE_CONF, Type.BOOLEAN, true, Importance.LOW, SALESFORCE_PUSH_TOPIC_CREATE_DOC)
        .define(SALESFORCE_PUSH_TOPIC_NOTIFY_CREATE_CONF, Type.BOOLEAN, true, Importance.LOW, SALESFORCE_PUSH_TOPIC_NOTIFY_CREATE_DOC)
        .define(SALESFORCE_PUSH_TOPIC_NOTIFY_UPDATE_CONF, Type.BOOLEAN, true, Importance.LOW, SALESFORCE_PUSH_TOPIC_NOTIFY_UPDATE_DOC)
        .define(SALESFORCE_PUSH_TOPIC_NOTIFY_DELETE_CONF, Type.BOOLEAN, true, Importance.LOW, SALESFORCE_PUSH_TOPIC_NOTIFY_DELETE_DOC)
        .define(SALESFORCE_PUSH_TOPIC_NOTIFY_UNDELETE_CONF, Type.BOOLEAN, true, Importance.LOW, SALESFORCE_PUSH_TOPIC_NOTIFY_UNDELETE_DOC);
  }

  public static final String TEMPLATE_NAME = "topicName";
  public final String username;
  public final String password;
  public final String passwordToken;
  public final String consumerKey;
  public final String consumerSecret;
  public final String instance;
  public final boolean curlLogging;
  public final StructTemplate kafkaTopicTemplate;
  public final String salesForcePushTopicName;
  public final boolean salesForcePushTopicCreate;
  public final String salesForceObject;
  public final long connectTimeout;
  public final boolean salesForcePushTopicNotifyCreate;
  public final boolean salesForcePushTopicNotifyUpdate;
  public final boolean salesForcePushTopicNotifyDelete;
  public final boolean salesForcePushTopicNotifyUndelete;
  public final String version;
  public final boolean kafkaTopicLowerCase;

}