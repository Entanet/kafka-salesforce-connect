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
package com.github.jcustenborder.kafka.connect.salesforce.rest;

import com.github.jcustenborder.kafka.connect.salesforce.rest.model.ApiVersion;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.PushTopic;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectDescriptor;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectMetadata;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.AuthenticationResponse;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectsResponse;

import java.util.List;

public interface SalesforceRestClient {
  AuthenticationResponse authenticate();

  void apiVersion(ApiVersion apiVersion);

  List<ApiVersion> apiVersions();

  SObjectsResponse objects();

  SObjectDescriptor describe(SObjectMetadata metadata);

  List<PushTopic> pushTopics();

  void pushTopic(PushTopic pushTopic);
}