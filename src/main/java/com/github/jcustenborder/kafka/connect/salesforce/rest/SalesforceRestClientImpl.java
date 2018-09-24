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
package com.github.jcustenborder.kafka.connect.salesforce.rest;

import com.github.jcustenborder.kafka.connect.salesforce.SalesforceSourceConnectorConfig;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.ApiVersion;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.ApiVersions;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.AuthenticationResponse;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.PushTopic;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.PushTopicQueryResult;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectDescriptor;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectMetadata;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SObjectsResponse;
import com.github.jcustenborder.kafka.connect.salesforce.rest.model.SalesforceException;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import org.apache.kafka.connect.errors.ConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

class SalesforceRestClientImpl implements SalesforceRestClient {
  static final Logger log = LoggerFactory.getLogger(SalesforceRestClientImpl.class);
  static final JsonFactory JSON_FACTORY = new JacksonFactory();
  final SalesforceSourceConnectorConfig config;
  final HttpRequestFactory requestFactory;
  final HttpTransport httpTransport;
  AuthenticationResponse authentication;
  GenericUrl baseUrl;
  GenericUrl versionBaseUrl;

  public SalesforceRestClientImpl(SalesforceSourceConnectorConfig config) {
    this(config, new NetHttpTransport());
  }

  public SalesforceRestClientImpl(final SalesforceSourceConnectorConfig config, HttpTransport httpTransport) {
    this.config = config;
    this.httpTransport = httpTransport;
    java.util.logging.Logger transportLogger = java.util.logging.Logger.getLogger(HttpTransport.class.getName());
    transportLogger.setLevel(Level.ALL);
    transportLogger.addHandler(new Handler() {
      @Override
      public void publish(LogRecord record) {
        if (Level.INFO.equals(record.getLevel())) {
          log.info(record.getMessage());
        } else if (Level.WARNING.equals(record.getLevel())) {
          log.warn(record.getMessage());
        } else if (Level.SEVERE.equals(record.getLevel())) {
          log.error(record.getMessage());
        }
        log.debug(record.getMessage());
      }

      @Override
      public void flush() {

      }

      @Override
      public void close() throws SecurityException {

      }
    });
    this.requestFactory = httpTransport.createRequestFactory(new HttpRequestInitializer() {
      @Override
      public void initialize(HttpRequest request) throws IOException {
        if (config.curlLogging) {
          request.setCurlLoggingEnabled(true);
          request.setLoggingEnabled(true);
        }

        request.setParser(new JsonObjectParser(JSON_FACTORY));
        if (null != authentication) {
          String headerValue = String.format("%s %s", authentication.tokenType(), authentication.accessToken());
          request.getHeaders().setAuthorization(headerValue);
        }
      }
    });
  }

  UrlEncodedContent buildAuthContent() {
    Map<String, String> content = new LinkedHashMap<>();
    content.put("grant_type", "password");
    content.put("client_id", this.config.consumerKey);
    content.put("client_secret", this.config.consumerSecret);
    content.put("username", this.config.username);
    String password = String.format("%s%s", this.config.password, this.config.passwordToken);
    content.put("password", password);
    return new UrlEncodedContent(content);
  }


  <T> T executeAndParse(HttpRequest request, Class<T> cls) throws IOException {
    if (log.isDebugEnabled()) {
      log.debug("Calling {} on {}", request.getRequestMethod(), request.getUrl());
    }
    HttpResponse response = request.execute();

    if (response.isSuccessStatusCode()) {
      return response.parseAs(cls);
    } else {
      SalesforceException exception = response.parseAs(SalesforceException.class);
      throw exception;
    }
  }

  <T> T getAndParse(GenericUrl url, Class<T> cls) {
    try {
      HttpRequest request = this.requestFactory.buildGetRequest(url);
      return executeAndParse(request, cls);
    } catch (IOException ex) {
      throw new ConnectException("Exception encountered while calling salesforce", ex);
    }
  }

  <T> T postAndParse(GenericUrl url, HttpContent content, Class<T> cls) {
    try {
      HttpRequest request = this.requestFactory.buildPostRequest(url, content);
      return executeAndParse(request, cls);
    } catch (IOException ex) {
      throw new ConnectException("Exception encountered while calling salesforce", ex);
    }
  }

  @Override
  public AuthenticationResponse authenticate() {
    HttpContent formContent = buildAuthContent();
    
    String authUrl = this.config.instance;
    if (null == authUrl || this.config.instance.isEmpty()) {
      authUrl = "https://login.salesforce.com";
    }

    GenericUrl authenticateUrl = new GenericUrl(authUrl);
    authenticateUrl.appendRawPath("/services/oauth2/token");
    this.authentication = postAndParse(authenticateUrl, formContent, AuthenticationResponse.class);
    this.baseUrl = new GenericUrl(this.authentication.instance_url());

    return this.authentication;
  }

  @Override
  public void apiVersion(ApiVersion apiVersion) {
    GenericUrl versionUrl = this.baseUrl.clone();
    versionUrl.appendRawPath(apiVersion.url());
    this.versionBaseUrl = versionUrl;
  }

  @Override
  public List<ApiVersion> apiVersions() {
    GenericUrl versionsUrl = this.baseUrl.clone();
    versionsUrl.appendRawPath("/services/data/");
    List<ApiVersion> apiVersions = getAndParse(versionsUrl, ApiVersions.class);
    Collections.sort(apiVersions);
    Collections.reverse(apiVersions);
    Preconditions.checkState(!apiVersions.isEmpty(), "No api versions were found.");
    return apiVersions;
  }


  @Override
  public SObjectsResponse objects() {
    GenericUrl objectsUrl = this.versionBaseUrl.clone();
    objectsUrl.appendRawPath("/sobjects/");

    SObjectsResponse response = getAndParse(objectsUrl, SObjectsResponse.class);


    return response;
  }


  @Override
  public SObjectDescriptor describe(SObjectMetadata metadata) {
    String describeUrlText = metadata.urls().get("describe");
    GenericUrl describeUrl = this.baseUrl.clone();
    describeUrl.appendRawPath(describeUrlText);
    return getAndParse(describeUrl, SObjectDescriptor.class);
  }

  @Override
  public List<PushTopic> pushTopics() {
    GenericUrl pushTopicsUrl = this.versionBaseUrl.clone();
    pushTopicsUrl.appendRawPath("/query/");
    pushTopicsUrl.set("q", "SELECT Name, Query, ApiVersion, NotifyForOperationCreate, NotifyForOperationUpdate, NotifyForOperationUndelete, NotifyForOperationDelete, NotifyForFields from PushTopic");

    PushTopicQueryResult queryResult = getAndParse(pushTopicsUrl, PushTopicQueryResult.class);
    return queryResult.records();
  }

  @Override
  public void pushTopic(PushTopic pushTopic) {
    JsonHttpContent httpContent = new JsonHttpContent(JSON_FACTORY, pushTopic);
    GenericUrl pushTopicsUrl = this.versionBaseUrl.clone();
    pushTopicsUrl.appendRawPath("/sobjects/PushTopic");

    postAndParse(pushTopicsUrl, httpContent, Map.class);
  }
}