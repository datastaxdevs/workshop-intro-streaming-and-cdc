[Home](index.md)

---

## Adding a source connector

In this module we will use the contents of a local file to write messages to a Pulsar topic.

1. Create the connector in Pulsar using the manifest

    ```bash
    ./bin/pulsar-admin sources create \
        --archive ../resources/pulsar-io-file-2.9.2.nar \
        --source-config-file ../resources/file-connector.yaml \
        --destination-topic-name file-source-topic
    ```

1. Get the status of the connector

    ```bash
    ./bin/pulsar-admin sources status --name "FileSource"
    ```

    The output should be similar to below. Note the `running` value should be `true`.

    ```yaml
    {
        "numInstances" : 1,
        "numRunning" : 0,
        "instances" : [{
            "instanceId" : 0,
            "status" : {
            "running" : true,
            "error" : "",
            "numRestarts" : 1,
            "numReceivedFromSource" : 0,
            "numSystemExceptions" : 0,
            "latestSystemExceptions" : [ ],
            "numSourceExceptions" : 0,
            "latestSourceExceptions" : [ ],
            "numWritten" : 0,
            "lastReceivedTime" : 0,
            "workerId" : "c-standalone-fw-localhost-8080"
            }
        }]
    }
    ```

## Adding and consuming message from the command line

1. Add 2 messages to the file

    ```bash
    echo "The first message" > ../resources/my-messages.txt
    echo "The second message" > ../resources/my-messages.txt
    ```

1. Consume the 2 messages

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 2 -s "file-source" persistent://public/default/file-source-topic
    ```

## Adding and consuming messages from the file and a consumer

1. Open the file "my-message.txt" from the "resources" directory in the left navigation

1. Start a consumer that runs indefinitly

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 0 -s "file-source" persistent://public/default/file-source-topic
    ```

1. Add text to the file and notice the message consumer prints the content

1. Add a new line to the file and add more text. The consumer prints the content.

    Note you don't need to save the file. It's a continuous stream!

1. Close the consumer by clicking in the terminal area and pressing ctrl+c

## Summary

This was really cool

---
[Next Module](./sink-connector.md)
