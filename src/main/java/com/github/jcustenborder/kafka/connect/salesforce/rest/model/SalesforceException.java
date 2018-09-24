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

public class SalesforceException extends RuntimeException {
  @Key("error")
  String error;
  @Key("error_description")
  String errorDescription;

  public String error() {
    return this.error;
  }

  public void error(String error) {
    this.error = error;
  }

  public String errorDescription() {
    return this.errorDescription;
  }

  public void errorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  @Override
  public String getMessage() {
    return String.format("%s: %s", this.error, this.errorDescription);
  }
}