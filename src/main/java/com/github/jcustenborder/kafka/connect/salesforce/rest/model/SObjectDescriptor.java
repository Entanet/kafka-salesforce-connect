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

import java.util.List;

public class SObjectDescriptor {

  @Key("actionOverrides")
  List<String> actionOverrides;
  @Key("activateable")
  Boolean activateable;
  @Key("compactLayoutable")
  Boolean compactLayoutable;
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
  String keyPrefix;
  @Key("label")
  String label;
  @Key("labelPlural")
  String labelPlural;
  @Key("layoutable")
  Boolean layoutable;
  @Key("listviewable")
  String listviewable;
  @Key("lookupLayoutable")
  String lookupLayoutable;
  @Key("mergeable")
  Boolean mergeable;
  @Key("mruEnabled")
  Boolean mruEnabled;
  @Key("name")
  String name;
  @Key("namedLayoutInfos")
  List<String> namedLayoutInfos;
  @Key("networkScopeFieldName")
  String networkScopeFieldName;
  @Key("queryable")
  Boolean queryable;
  @Key("replicateable")
  Boolean replicateable;
  @Key("retrieveable")
  Boolean retrieveable;
  @Key("searchLayoutable")
  Boolean searchLayoutable;
  @Key("searchable")
  Boolean searchable;
  @Key("triggerable")
  Boolean triggerable;
  @Key("undeletable")
  Boolean undeletable;
  @Key("updateable")
  Boolean updateable;
  @Key("fields")
  List<Field> fields;

  public List<String> actionOverrides() {
    return this.actionOverrides;
  }

  public void actionOverrides(List<String> actionOverrides) {
    this.actionOverrides = actionOverrides;
  }

  public Boolean activateable() {
    return this.activateable;
  }

  public void activateable(Boolean activateable) {
    this.activateable = activateable;
  }

  public Boolean compactLayoutable() {
    return this.compactLayoutable;
  }

  public void compactLayoutable(Boolean compactLayoutable) {
    this.compactLayoutable = compactLayoutable;
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

  public String keyPrefix() {
    return this.keyPrefix;
  }

  public void keyPrefix(String keyPrefix) {
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

  public String listviewable() {
    return this.listviewable;
  }

  public void listviewable(String listviewable) {
    this.listviewable = listviewable;
  }

  public String lookupLayoutable() {
    return this.lookupLayoutable;
  }

  public void lookupLayoutable(String lookupLayoutable) {
    this.lookupLayoutable = lookupLayoutable;
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

  public List<String> namedLayoutInfos() {
    return this.namedLayoutInfos;
  }

  public void namedLayoutInfos(List<String> namedLayoutInfos) {
    this.namedLayoutInfos = namedLayoutInfos;
  }

  public String networkScopeFieldName() {
    return this.networkScopeFieldName;
  }

  public void networkScopeFieldName(String networkScopeFieldName) {
    this.networkScopeFieldName = networkScopeFieldName;
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

  public Boolean searchLayoutable() {
    return this.searchLayoutable;
  }

  public void searchLayoutable(Boolean searchLayoutable) {
    this.searchLayoutable = searchLayoutable;
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

  public List<Field> fields() {
    return this.fields;
  }

  public void fields(List<Field> fields) {
    this.fields = fields;
  }


  public static class Field {

    //"aggregatable": true,
    @Key("aggregatable")
    Boolean aggregatable;

    //"autoNumber": false,
    @Key("autoNumber")
    Boolean autoNumber;

    //"byteLength": 18,
    @Key("byteLength")
    Integer byteLength;

    //"calculated": false,
    @Key("calculated")
    Boolean calculated;

    //"calculatedFormula": null,
    @Key("calculatedFormula")
    Object calculatedFormula;

    //"cascadeDelete": false,
    @Key("cascadeDelete")
    Boolean cascadeDelete;

    //"caseSensitive": false,
    @Key("caseSensitive")
    Boolean caseSensitive;

    //"controllerName": null,
    @Key("controllerName")
    String controllerName;

    //"createable": false,
    @Key("createable")
    Boolean createable;

    //"custom": false,
    @Key("custom")
    Boolean custom;

    //"defaultValue": null,
    @Key("defaultValue")
    Object defaultValue;

    //"defaultValueFormula": null,
    @Key("defaultValueFormula")
    Object defaultValueFormula;

    //"defaultedOnCreate": true,
    @Key("defaultedOnCreate")
    Boolean defaultedOnCreate;

    //"dependentPicklist": false,
    @Key("dependentPicklist")
    Boolean dependentPicklist;

    //"deprecatedAndHidden": false,
    @Key("deprecatedAndHidden")
    Boolean deprecatedAndHidden;

    //"digits": 0,
    @Key("digits")
    Integer digits;

    //"displayLocationInDecimal": false,
    @Key("displayLocationInDecimal")
    Boolean displayLocationInDecimal;

    //"encrypted": false,
    @Key("encrypted")
    Boolean encrypted;

    //"externalId": false,
    @Key("externalId")
    Boolean externalId;

    //"extraTypeInfo": null,
    @Key("extraTypeInfo")
    Object extraTypeInfo;

    //"filterable": true,
    @Key("filterable")
    Boolean filterable;

    //"filteredLookupInfo": null,
    @Key("filteredLookupInfo")
    Object filteredLookupInfo;

    //"groupable": true,
    @Key("groupable")
    Boolean groupable;

    //"highScaleNumber": false,
    @Key("highScaleNumber")
    Boolean highScaleNumber;

    //"htmlFormatted": false,
    @Key("htmlFormatted")
    Boolean htmlFormatted;

    //"idLookup": true,
    @Key("idLookup")
    Boolean idLookup;

    //"inlineHelpText": null,
    @Key("inlineHelpText")
    String inlineHelpText;

    //"label": "Lead ID",
    @Key("label")
    String label;

    //"length": 18,
    @Key("length")
    Integer length;

    //"mask": null,
    @Key("mask")
    String mask;

    //"maskType": null,
    @Key("maskType")
    String maskType;

    //"name": "Id",
    @Key("name")
    String name;

    //"nameField": false,
    @Key("nameField")
    Boolean nameField;

    //"namePointing": false,
    @Key("namePointing")
    Boolean namePointing;

    //"nillable": false,
    @Key("nillable")
    Boolean nillable;

    //"permissionable": false,
    @Key("permissionable")
    Boolean permissionable;

    //"picklistValues": [],
    @Key("picklistValues")
    List<String> picklistValues;

    //"precision": 0,
    @Key("precision")
    Integer precision;

    //"queryByDistance": false,
    @Key("queryByDistance")
    Boolean queryByDistance;

    //"referenceTargetField": null,
    @Key("referenceTargetField")
    Object referenceTargetField;

    //"referenceTo": [],
    @Key("referenceTo")
    List<String> referenceTo;

    //"relationshipName": null,
    @Key("relationshipName")
    String relationshipName;

    //"relationshipOrder": null,
    @Key("relationshipOrder")
    String relationshipOrder;

    //"restrictedDelete": false,
    @Key("restrictedDelete")
    Boolean restrictedDelete;

    //"restrictedPicklist": false,
    @Key("restrictedPicklist")
    Boolean restrictedPicklist;

    //"scale": 0,
    @Key("scale")
    Integer scale;

    //"soapType": "tns:ID",
    @Key("soapType")
    String soapType;

    //"sortable": true,
    @Key("sortable")
    Boolean sortable;

    //"type": "id",
    @Key("type")
    String type;

    //"unique": false,
    @Key("unique")
    Boolean unique;

    //"updateable": false,
    @Key("updateable")
    Boolean updateable;

    //"writeRequiresMasterRead":false,
    @Key("writeRequiresMasterRead")
    Boolean writeRequiresMasterRead;


    public Boolean aggregatable() {
      return this.aggregatable;
    }

    public void aggregatable(Boolean aggregatable) {
      this.aggregatable = aggregatable;
    }

    public Boolean autoNumber() {
      return this.autoNumber;
    }

    public void autoNumber(Boolean autoNumber) {
      this.autoNumber = autoNumber;
    }

    public Integer byteLength() {
      return this.byteLength;
    }

    public void byteLength(Integer byteLength) {
      this.byteLength = byteLength;
    }

    public Boolean calculated() {
      return this.calculated;
    }

    public void calculated(Boolean calculated) {
      this.calculated = calculated;
    }

    public Object calculatedFormula() {
      return this.calculatedFormula;
    }

    public void calculatedFormula(Object calculatedFormula) {
      this.calculatedFormula = calculatedFormula;
    }

    public Boolean cascadeDelete() {
      return this.cascadeDelete;
    }

    public void cascadeDelete(Boolean cascadeDelete) {
      this.cascadeDelete = cascadeDelete;
    }

    public Boolean caseSensitive() {
      return this.caseSensitive;
    }

    public void caseSensitive(Boolean caseSensitive) {
      this.caseSensitive = caseSensitive;
    }

    public String controllerName() {
      return this.controllerName;
    }

    public void controllerName(String controllerName) {
      this.controllerName = controllerName;
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

    public Object defaultValue() {
      return this.defaultValue;
    }

    public void defaultValue(Object defaultValue) {
      this.defaultValue = defaultValue;
    }

    public Object defaultValueFormula() {
      return this.defaultValueFormula;
    }

    public void defaultValueFormula(Object defaultValueFormula) {
      this.defaultValueFormula = defaultValueFormula;
    }

    public Boolean defaultedOnCreate() {
      return this.defaultedOnCreate;
    }

    public void defaultedOnCreate(Boolean defaultedOnCreate) {
      this.defaultedOnCreate = defaultedOnCreate;
    }

    public Boolean dependentPicklist() {
      return this.dependentPicklist;
    }

    public void dependentPicklist(Boolean dependentPicklist) {
      this.dependentPicklist = dependentPicklist;
    }

    public Boolean deprecatedAndHidden() {
      return this.deprecatedAndHidden;
    }

    public void deprecatedAndHidden(Boolean deprecatedAndHidden) {
      this.deprecatedAndHidden = deprecatedAndHidden;
    }

    public Integer digits() {
      return this.digits;
    }

    public void digits(Integer digits) {
      this.digits = digits;
    }

    public Boolean displayLocationInDecimal() {
      return this.displayLocationInDecimal;
    }

    public void displayLocationInDecimal(Boolean displayLocationInDecimal) {
      this.displayLocationInDecimal = displayLocationInDecimal;
    }

    public Boolean encrypted() {
      return this.encrypted;
    }

    public void encrypted(Boolean encrypted) {
      this.encrypted = encrypted;
    }

    public Boolean externalId() {
      return this.externalId;
    }

    public void externalId(Boolean externalId) {
      this.externalId = externalId;
    }

    public Object extraTypeInfo() {
      return this.extraTypeInfo;
    }

    public void extraTypeInfo(Object extraTypeInfo) {
      this.extraTypeInfo = extraTypeInfo;
    }

    public Boolean filterable() {
      return this.filterable;
    }

    public void filterable(Boolean filterable) {
      this.filterable = filterable;
    }

    public Object filteredLookupInfo() {
      return this.filteredLookupInfo;
    }

    public void filteredLookupInfo(Object filteredLookupInfo) {
      this.filteredLookupInfo = filteredLookupInfo;
    }

    public Boolean groupable() {
      return this.groupable;
    }

    public void groupable(Boolean groupable) {
      this.groupable = groupable;
    }

    public Boolean highScaleNumber() {
      return this.highScaleNumber;
    }

    public void highScaleNumber(Boolean highScaleNumber) {
      this.highScaleNumber = highScaleNumber;
    }

    public Boolean htmlFormatted() {
      return this.htmlFormatted;
    }

    public void htmlFormatted(Boolean htmlFormatted) {
      this.htmlFormatted = htmlFormatted;
    }

    public Boolean idLookup() {
      return this.idLookup;
    }

    public void idLookup(Boolean idLookup) {
      this.idLookup = idLookup;
    }

    public String inlineHelpText() {
      return this.inlineHelpText;
    }

    public void inlineHelpText(String inlineHelpText) {
      this.inlineHelpText = inlineHelpText;
    }

    public String label() {
      return this.label;
    }

    public void label(String label) {
      this.label = label;
    }

    public Integer length() {
      return this.length;
    }

    public void length(Integer length) {
      this.length = length;
    }

    public String mask() {
      return this.mask;
    }

    public void mask(String mask) {
      this.mask = mask;
    }

    public String maskType() {
      return this.maskType;
    }

    public void maskType(String maskType) {
      this.maskType = maskType;
    }

    public String name() {
      return this.name;
    }

    public void name(String name) {
      this.name = name;
    }

    public Boolean nameField() {
      return this.nameField;
    }

    public void nameField(Boolean nameField) {
      this.nameField = nameField;
    }

    public Boolean namePointing() {
      return this.namePointing;
    }

    public void namePointing(Boolean namePointing) {
      this.namePointing = namePointing;
    }

    public Boolean nillable() {
      return this.nillable;
    }

    public void nillable(Boolean nillable) {
      this.nillable = nillable;
    }

    public Boolean permissionable() {
      return this.permissionable;
    }

    public void permissionable(Boolean permissionable) {
      this.permissionable = permissionable;
    }

    public List<String> picklistValues() {
      return this.picklistValues;
    }

    public void picklistValues(List<String> picklistValues) {
      this.picklistValues = picklistValues;
    }

    public Integer precision() {
      return this.precision;
    }

    public void precision(Integer precision) {
      this.precision = precision;
    }

    public Boolean queryByDistance() {
      return this.queryByDistance;
    }

    public void queryByDistance(Boolean queryByDistance) {
      this.queryByDistance = queryByDistance;
    }

    public Object referenceTargetField() {
      return this.referenceTargetField;
    }

    public void referenceTargetField(Object referenceTargetField) {
      this.referenceTargetField = referenceTargetField;
    }

    public List<String> referenceTo() {
      return this.referenceTo;
    }

    public void referenceTo(List<String> referenceTo) {
      this.referenceTo = referenceTo;
    }

    public String relationshipName() {
      return this.relationshipName;
    }

    public void relationshipName(String relationshipName) {
      this.relationshipName = relationshipName;
    }

    public String relationshipOrder() {
      return this.relationshipOrder;
    }

    public void relationshipOrder(String relationshipOrder) {
      this.relationshipOrder = relationshipOrder;
    }

    public Boolean restrictedDelete() {
      return this.restrictedDelete;
    }

    public void restrictedDelete(Boolean restrictedDelete) {
      this.restrictedDelete = restrictedDelete;
    }

    public Boolean restrictedPicklist() {
      return this.restrictedPicklist;
    }

    public void restrictedPicklist(Boolean restrictedPicklist) {
      this.restrictedPicklist = restrictedPicklist;
    }

    public Integer scale() {
      return this.scale;
    }

    public void scale(Integer scale) {
      this.scale = scale;
    }

    public String soapType() {
      return this.soapType;
    }

    public void soapType(String soapType) {
      this.soapType = soapType;
    }

    public Boolean sortable() {
      return this.sortable;
    }

    public void sortable(Boolean sortable) {
      this.sortable = sortable;
    }

    public String type() {
      return this.type;
    }

    public void type(String type) {
      this.type = type;
    }

    public Boolean unique() {
      return this.unique;
    }

    public void unique(Boolean unique) {
      this.unique = unique;
    }

    public Boolean updateable() {
      return this.updateable;
    }

    public void updateable(Boolean updateable) {
      this.updateable = updateable;
    }

    public Boolean writeRequiresMasterRead() {
      return this.writeRequiresMasterRead;
    }

    public void writeRequiresMasterRead(Boolean writeRequiresMasterRead) {
      this.writeRequiresMasterRead = writeRequiresMasterRead;
    }
  }
}