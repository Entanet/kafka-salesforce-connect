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

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jcustenborder.kafka.connect.utils.data.SourceRecordConcurrentLinkedDeque;
import com.github.jcustenborder.kafka.connect.utils.jackson.ObjectMapperFactory;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TopicChannelMessageListener implements ClientSessionChannel.MessageListener {
  private static final Logger log = LoggerFactory.getLogger(TopicChannelMessageListener.class);
  private final SourceRecordConcurrentLinkedDeque records;
  private final SalesforceSourceConnectorConfig config;
  private final Schema keySchema;
  private final Schema valueSchema;
  private final SObjectHelper sObjectHelper;

  TopicChannelMessageListener(SourceRecordConcurrentLinkedDeque records, SalesforceSourceConnectorConfig config, Schema keySchema, Schema valueSchema) {
    this.records = records;
    this.config = config;
    this.keySchema = keySchema;
    this.valueSchema = valueSchema;
    this.sObjectHelper = new SObjectHelper(config, keySchema, valueSchema);
  }

  @Override
  public void onMessage(ClientSessionChannel clientSessionChannel, Message message) {
    try {
      String jsonMessage = message.getJSON();
      log.trace("onMessage() - jsonMessage = {}", jsonMessage);
      JsonNode jsonNode = ObjectMapperFactory.INSTANCE.readTree(jsonMessage);
      SourceRecord record = this.sObjectHelper.convert(jsonNode);
      this.records.add(record);
    } catch (Exception ex) {
      log.error("Exception thrown while processing message.", ex);
    }
  }

}