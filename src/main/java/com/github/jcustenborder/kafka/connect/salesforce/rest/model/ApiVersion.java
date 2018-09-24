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

import java.math.BigDecimal;

public class ApiVersion implements Comparable<ApiVersion> {
  @Key("label")
  String label;
  @Key("url")
  String url;
  @Key("version")
  String version;

  public String label() {
    return this.label;
  }

  public void label(String label) {
    this.label = label;
  }

  public String url() {
    return this.url;
  }

  public void url(String url) {
    this.url = url;
  }

  public String version() {
    return this.version;
  }

  public void version(String version) {
    this.version = version;
  }

  @Override
  public int compareTo(ApiVersion that) {
    if (null == that) {
      return 1;
    }

    BigDecimal thisVersion = new BigDecimal(this.version).setScale(3);
    BigDecimal thatVersion = new BigDecimal(that.version).setScale(3);

    return thisVersion.compareTo(thatVersion);
  }
}