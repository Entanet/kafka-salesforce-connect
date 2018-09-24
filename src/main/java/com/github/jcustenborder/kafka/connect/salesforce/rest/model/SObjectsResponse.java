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
package com.github.jcustenborder.kafka.connect.salesforce.rest.model;

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

public class SObjectsResponse {
  @Key("encoding")
  String encoding;
  @Key("maxBatchSize")
  Integer maxBatchSize;
  @Key("sobjects")
  List<SObjectMetadata> sobjects = new ArrayList<>();

  public String encoding() {
    return this.encoding;
  }

  public void encoding(String encoding) {
    this.encoding = encoding;
  }

  public Integer maxBatchSize() {
    return this.maxBatchSize;
  }

  public void maxBatchSize(Integer maxBatchSize) {
    this.maxBatchSize = maxBatchSize;
  }

  public List<SObjectMetadata> sobjects() {
    return this.sobjects;
  }

  public void sobjects(List<SObjectMetadata> sobjects) {
    this.sobjects = sobjects;
  }
}