[Home](index.md)

---

## Adding a sink connector

In this module we will use a free account from [Elastic.co](Elastic.co) to create a sink of type Elasticsearch. Then we will publish a message to a topic that the sink is subscribed to and observe the message automatically pushed to Elastic.

1. Replace the values in the manifest with your Elastic deployment info

    ```yaml
    configs:
        elasticSearchUrl: "<REPLACE>"
        password: "<REPLACE>"
    ```

1. Create the sink in Pulsar using the manifest

    ```bash
    ./bin/pulsar-admin sinks create \
        --archive ../resources/pulsar-io-elastic-search-2.9.2.nar
        --sink-config-file ../resources/elasticsearch-sink.yaml
    ```

## Starting the sink connector and sending messages

1. Start the sink conenctor

    ```bash
    ./bin/pulsar-admin sinks status --name "ElasticSink"
    ```

1. Confirm the sink is running

    ```bash
    ./bin/pulsar-admin sinks start --name "ElasticSink"
    ```

1. Send a message to the topic being monitored by the sink

    ```bash
    ./bin/pulsar-client produce -m "This is a sink message" -n 1 persistent://public/default/es-sink-topic
    ```

## Observing the messages in Elastic.co account

1. In your Elastic.co account deployment choose "Management" > "Stack Management" from the left menu

    ![image12](https://user-images.githubusercontent.com/16946028/160866704-0d6dacfc-c35e-4271-b957-74fa314e37d9.png)

1. Then choose "Kibana" > "Data Views" to get a prompt that you already have data in ElasticSearch. Choose "Create data view".

    ![image5](https://user-images.githubusercontent.com/16946028/160866922-3609aeb4-a1a4-4a87-935e-751cf6d21250.png)

1. Name the data view to match the existing index "cdc_account_messages" and "Create data view"

    ![image14](https://user-images.githubusercontent.com/16946028/160867122-8e83732f-7531-44d9-a321-c266a74091d2.png)

1. Navigate to the "Discover" option of Analytics from the left menu

    ![image6](https://user-images.githubusercontent.com/16946028/160867343-60ec87ac-db5c-461d-8a92-6307e752b14b.png)

1. The new message added to the topic will be displaied

    ![image6](xxx)

## Summary

This was really cool

---
[Home](./index.md)
