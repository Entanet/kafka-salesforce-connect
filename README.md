# Overview

This Kafka Connect connector integrates with the [Salesforce Streaming Api](https://developer.salesforce.com/docs/atlas.en-us.api_streaming.meta/api_streaming/intro_stream.htm)
to provide realtime updates to Kafka as objects are modified in SalesForce. This is done by registering a [PushTopic](https://developer.salesforce.com/docs/atlas.en-us.api_streaming.meta/api_streaming/working_with_pushtopics.htm) 
for specific objects and fields. This object can be managed externally from the connector or you can specify `salesforce.push.topic.create=true` which will 
query the descriptor for the object specified in `salesforce.object`, generating a PushTopic for all of the fields in the descriptor. The descriptor will contain all of the fields that are available. The dynamically generated PushTopic
will use all of the fields that are available at the time the PushTopic is created. If you manually create a PushTopic the schema will still include all of the fields that are defined in the descriptor.

# Breaking change in 0.3.x

[Issue-14](https://github.com/jcustenborder/kafka-connect-salesforce/issues/14) introduces a required incompatible change. 
The address type was incorrectly identified as a string. This has been corrected to a child object.


# Configuration

## SalesforceSourceConnector

```properties
name=connector1
tasks.max=1
connector.class=com.github.jcustenborder.kafka.connect.salesforce.SalesforceSourceConnector

# Set these required values
salesforce.username=
salesforce.consumer.key=
salesforce.push.topic.name=
salesforce.password=
salesforce.password.token=
kafka.topic=
salesforce.consumer.secret=
salesforce.object=
```

| Name                                  | Description                                                                                                                                                                                                                                                                                                                                                                                                                          | Type     | Default | Valid Values                             | Importance |
|---------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|---------|------------------------------------------|------------|
| kafka.topic                           | The Kafka topic to write the SalesForce data to. This is a template driven by the data returned by Salesforce. Any field in the schema can be used but you should always pick a value that is guarenteed to be there. `_EventType` and `_ObjectType` are two metadata fields that are included on every record. For example you could put update and deletes in a different topic by using `salesforce.${_ObjectType}.${_EventType}` | string   |         |                                          | high       |
| salesforce.consumer.key               | The consumer key for the OAuth application.                                                                                                                                                                                                                                                                                                                                                                                          | string   |         |                                          | high       |
| salesforce.consumer.secret            | The consumer secret for the OAuth application.                                                                                                                                                                                                                                                                                                                                                                                       | password |         |                                          | high       |
| salesforce.object                     | The Salesforce object to create a topic for.                                                                                                                                                                                                                                                                                                                                                                                         | string   |         |                                          | high       |
| salesforce.password                   | Salesforce password to connect with.                                                                                                                                                                                                                                                                                                                                                                                                 | password |         |                                          | high       |
| salesforce.password.token             | The Salesforce security token associated with the username.                                                                                                                                                                                                                                                                                                                                                                          | password |         |                                          | high       |
| salesforce.push.topic.name            | The Salesforce topic to subscribe to. If salesforce.push.topic.create is set to true, a PushTopic with this name will be created.                                                                                                                                                                                                                                                                                                    | string   |         |                                          | high       |
| salesforce.username                   | Salesforce username to connect with.                                                                                                                                                                                                                                                                                                                                                                                                 | string   |         |                                          | high       |
| kafka.topic.lowercase                 | Flag to determine if the kafka topic should be lowercased.                                                                                                                                                                                                                                                                                                                                                                           | boolean  | true    |                                          | high       |
| salesforce.instance                   | The Salesforce instance to connect to.                                                                                                                                                                                                                                                                                                                                                                                               | string   | ""      |                                          | high       |
| connection.timeout                    | The amount of time to wait while connecting to the Salesforce streaming endpoint.                                                                                                                                                                                                                                                                                                                                                    | long     | 30000   | [5000,...,600000]                        | low        |
| curl.logging                          | If enabled the logs will output the equivalent curl commands. This is a security risk because your authorization header will end up in the log file. Use at your own risk.                                                                                                                                                                                                                                                           | boolean  | false   |                                          | low        |
| salesforce.push.topic.create          | Flag to determine if the PushTopic should be created if it does not exist.                                                                                                                                                                                                                                                                                                                                                           | boolean  | true    |                                          | low        |
| salesforce.push.topic.notify.create   | Flag to determine if the PushTopic should respond to creates.                                                                                                                                                                                                                                                                                                                                                                        | boolean  | true    |                                          | low        |
| salesforce.push.topic.notify.delete   | Flag to determine if the PushTopic should respond to deletes.                                                                                                                                                                                                                                                                                                                                                                        | boolean  | true    |                                          | low        |
| salesforce.push.topic.notify.undelete | Flag to determine if the PushTopic should respond to undeletes.                                                                                                                                                                                                                                                                                                                                                                      | boolean  | true    |                                          | low        |
| salesforce.push.topic.notify.update   | Flag to determine if the PushTopic should respond to updates.                                                                                                                                                                                                                                                                                                                                                                        | boolean  | true    |                                          | low        |
| salesforce.version                    | The version of the salesforce API to use.                                                                                                                                                                                                                                                                                                                                                                                            | string   | latest  | ValidPattern{pattern=^(latest|[\d\.]+)$} | low        |


# Building

```
git clone https://github.com/jcustenborder/kafka-connect-salesforce
cd kafka-connect-salesforce
mvn clean install
```

# Running in development

```
mvn clean package
export CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')"
$CONFLUENT_HOME/bin/connect-standalone $CONFLUENT_HOME/etc/schema-registry/connect-avro-standalone.properties config/MySourceConnector.properties
```

# Example Output

This is the output from the `kafka-avro-console-consumer`. This schema is dynamically generated based on the [Object metadata](https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/dome_sobject_basic_info.htm) rest api.

```
{
  "Id": "00Q5000001BqAICEA3",
  "IsDeleted": {
    "boolean": false
  },
  "MasterRecordId": null,
  "LastName": {
    "string": "Smith"
  },
  "FirstName": {
    "string": "Fred"
  },
  "Salutation": null,
  "Name": {
    "string": "Fred Smith"
  },
  "Title": {
    "string": "CEO"
  },
  "Company": {
    "string": "Testing Company"
  },
  "City": {
    "string": "New York"
  },
  "State": {
    "string": "NY"
  },
  "PostalCode": {
    "string": "12345"
  },
  "Country": null,
  "Latitude": null,
  "Longitude": null,
  "GeocodeAccuracy": null,
  "Address": {
    "com.github.jcustenborder.kafka.connect.salesforce.Address": {
      "GeocodeAccuracy": null,
      "State": {
        "string": "NY"
      },
      "Street": {
        "string": "123 Wall St"
      },
      "PostalCode": {
        "string": "12345"
      },
      "Country": null,
      "Latitude": null,
      "City": {
        "string": "New York"
      },
      "Longitude": null
    }
  },
  "Phone": {
    "string": "555-867-5309"
  },
  "MobilePhone": {
    "string": "000-000-0000"
  },
  "Fax": null,
  "Email": {
    "string": "fred.smith@example.com"
  },
  "Website": null,
  "PhotoUrl": null,
  "LeadSource": {
    "string": "Web"
  },
  "Status": {
    "string": "Open - Not Contacted"
  },
  "Industry": {
    "string": "Transportation"
  },
  "Rating": null,
  "AnnualRevenue": {
    "string": "100000000"
  },
  "NumberOfEmployees": {
    "int": 100
  },
  "OwnerId": {
    "string": "00550000005elXkAAI"
  },
  "IsConverted": {
    "boolean": false
  },
  "ConvertedDate": null,
  "ConvertedAccountId": null,
  "ConvertedContactId": null,
  "ConvertedOpportunityId": null,
  "IsUnreadByOwner": {
    "boolean": false
  },
  "CreatedDate": {
    "long": 1483228800000
  },
  "CreatedById": {
    "string": "00550000005elXkAAI"
  },
  "LastModifiedDate": {
    "long": 1483315200000
  },
  "LastModifiedById": {
    "string": "00550000005elXkAAI"
  },
  "SystemModstamp": {
    "long": 1483315200000
  },
  "LastActivityDate": null,
  "LastViewedDate": null,
  "LastReferencedDate": null,
  "Jigsaw": null,
  "JigsawContactId": null,
  "CleanStatus": {
    "string": "5"
  },
  "CompanyDunsNumber": null,
  "DandbCompanyId": null,
  "EmailBouncedReason": null,
  "EmailBouncedDate": null,
  "SICCode__c": null,
  "ProductInterest__c": {
    "string": "GC1000 series"
  },
  "Primary__c": null,
  "CurrentGenerators__c": null,
  "NumberofLocations__c": null,
  "_ObjectType": {
    "string": "Lead"
  },
  "_EventType": {
    "string": "updated"
  }
}
```