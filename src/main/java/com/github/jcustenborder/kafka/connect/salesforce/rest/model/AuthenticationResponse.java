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

public class AuthenticationResponse {
  @Key("access_token")
  String accessToken;
  @Key("instance_url")
  String instanceUrl;
  @Key("id")
  String id;
  @Key("token_type")
  String tokenType;
  @Key("issuedAt")
  String issuedAt;
  @Key("signature")
  String signature;


  public String accessToken() {
    return this.accessToken;
  }

  public void accessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String instance_url() {
    return this.instanceUrl;
  }

  public void instance_url(String instanceUrl) {
    this.instanceUrl = instanceUrl;
  }

  public String id() {
    return this.id;
  }

  public void id(String id) {
    this.id = id;
  }

  public String tokenType() {
    return this.tokenType;
  }

  public void tokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String issuedAt() {
    return this.issuedAt;
  }

  public void issuedAt(String issuedAt) {
    this.issuedAt = issuedAt;
  }

  public String signature() {
    return this.signature;
  }

  public void signature(String signature) {
    this.signature = signature;
  }
}