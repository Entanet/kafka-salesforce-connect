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

import java.util.Map;

public class SalesForceEvent {
  @Key("clientId")
  String clientId;
  @Key("data")
  Data data;
  @Key("Channel")
  String channel;

  public String clientId() {
    return this.clientId;
  }

  public void clientId(String clientId) {
    this.clientId = clientId;
  }

  public Data data() {
    return this.data;
  }

  public void data(Data data) {
    this.data = data;
  }

  public String channel() {
    return this.channel;
  }

  public void channel(String channel) {
    this.channel = channel;
  }

  public static class Data {
    @Key("event")
    Event event;
    @Key("sobject")
    Map<String, Object> sobject;

    public Event event() {
      return this.event;
    }

    public void event(Event event) {
      this.event = event;
    }

    public Map<String, Object> sobject() {
      return this.sobject;
    }

    public void sobject(Map<String, Object> sobject) {
      this.sobject = sobject;
    }
  }

  public static class Event {
    @Key("createdDate")
    String createdDate;
    @Key("replayId")
    Long replayId;
    @Key("type")
    String type;

    public String createdDate() {
      return this.createdDate;
    }

    public void createdDate(String createdDate) {
      this.createdDate = createdDate;
    }

    public Long replayId() {
      return this.replayId;
    }

    public void replayId(Long replayId) {
      this.replayId = replayId;
    }

    public String type() {
      return this.type;
    }

    public void type(String type) {
      this.type = type;
    }
  }
}