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

public class SObjectMetadata {
  @Key("activateable")
  Boolean activateable;
  @Key("createable")
  Boolean createable;
  @Key("custom")
  Boolean custom;
  @Key("customSetting")
  Boolean customSetting;
  @Key("deletable")
  Boolean deletable;
  @Key("deprecatedAndHidden")
  Boolean deprecatedAndHidden;
  @Key("feedEnabled")
  Boolean feedEnabled;
  @Key("keyPrefix")
  Boolean keyPrefix;
  @Key("label")
  String label;
  @Key("labelPlural")
  String labelPlural;
  @Key("layoutable")
  Boolean layoutable;
  @Key("mergeable")
  Boolean mergeable;
  @Key("mruEnabled")
  Boolean mruEnabled;
  @Key("name")
  String name;
  @Key("queryable")
  Boolean queryable;
  @Key("replicateable")
  Boolean replicateable;
  @Key("retrieveable")
  Boolean retrieveable;
  @Key("searchable")
  Boolean searchable;
  @Key("triggerable")
  Boolean triggerable;
  @Key("undeletable")
  Boolean undeletable;
  @Key("updateable")
  Boolean updateable;
  @Key("urls")
  Map<String, String> urls;


  public Boolean activateable() {
    return this.activateable;
  }

  public void activateable(Boolean activateable) {
    this.activateable = activateable;
  }

  public Boolean createable() {
    return this.createable;
  }

  public void createable(Boolean createable) {
    this.createable = createable;
  }

  public Boolean custom() {
    return this.custom;
  }

  public void custom(Boolean custom) {
    this.custom = custom;
  }

  public Boolean customSetting() {
    return this.customSetting;
  }

  public void customSetting(Boolean customSetting) {
    this.customSetting = customSetting;
  }

  public Boolean deletable() {
    return this.deletable;
  }

  public void deletable(Boolean deletable) {
    this.deletable = deletable;
  }

  public Boolean deprecatedAndHidden() {
    return this.deprecatedAndHidden;
  }

  public void deprecatedAndHidden(Boolean deprecatedAndHidden) {
    this.deprecatedAndHidden = deprecatedAndHidden;
  }

  public Boolean feedEnabled() {
    return this.feedEnabled;
  }

  public void feedEnabled(Boolean feedEnabled) {
    this.feedEnabled = feedEnabled;
  }

  public Boolean keyPrefix() {
    return this.keyPrefix;
  }

  public void keyPrefix(Boolean keyPrefix) {
    this.keyPrefix = keyPrefix;
  }

  public String label() {
    return this.label;
  }

  public void label(String label) {
    this.label = label;
  }

  public String labelPlural() {
    return this.labelPlural;
  }

  public void labelPlural(String labelPlural) {
    this.labelPlural = labelPlural;
  }

  public Boolean layoutable() {
    return this.layoutable;
  }

  public void layoutable(Boolean layoutable) {
    this.layoutable = layoutable;
  }

  public Boolean mergeable() {
    return this.mergeable;
  }

  public void mergeable(Boolean mergeable) {
    this.mergeable = mergeable;
  }

  public Boolean mruEnabled() {
    return this.mruEnabled;
  }

  public void mruEnabled(Boolean mruEnabled) {
    this.mruEnabled = mruEnabled;
  }

  public String name() {
    return this.name;
  }

  public void name(String name) {
    this.name = name;
  }

  public Boolean queryable() {
    return this.queryable;
  }

  public void queryable(Boolean queryable) {
    this.queryable = queryable;
  }

  public Boolean replicateable() {
    return this.replicateable;
  }

  public void replicateable(Boolean replicateable) {
    this.replicateable = replicateable;
  }

  public Boolean retrieveable() {
    return this.retrieveable;
  }

  public void retrieveable(Boolean retrieveable) {
    this.retrieveable = retrieveable;
  }

  public Boolean searchable() {
    return this.searchable;
  }

  public void searchable(Boolean searchable) {
    this.searchable = searchable;
  }

  public Boolean triggerable() {
    return this.triggerable;
  }

  public void triggerable(Boolean triggerable) {
    this.triggerable = triggerable;
  }

  public Boolean undeletable() {
    return this.undeletable;
  }

  public void undeletable(Boolean undeletable) {
    this.undeletable = undeletable;
  }

  public Boolean updateable() {
    return this.updateable;
  }

  public void updateable(Boolean updateable) {
    this.updateable = updateable;
  }

  public Map<String, String> urls() {
    return this.urls;
  }

  public void urls(Map<String, String> urls) {
    this.urls = urls;
  }
}