[Home](index.md)

---

## Adding a source connector

In this module we will create a "source" that instructs the Pulsar Broker to "watch" a certain file. When new lines of content are added to the file, Pulsar will stream the contents to a topic.

1. Create the source connector in Pulsar using the provided manifest

    Similar to functions, notice the "parallelism" value. To accomodate high loads, Pulsar can run the same source in multiple backgound threads. There are many types of build in sources like Debezium, DynamoDB, Kafka, Kinesis, and RabbitMQ. [The documentation](https://pulsar.apache.org/docs/en/io-connectors/) has a list of all supported connectors.

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

## Adding and consuming messages from the command line

1. Add 2 messages to the file

    ```bash
    echo "The first message" > ../resources/my-messages.txt
    echo "The second message" > ../resources/my-messages.txt
    ```

1. Consume the 2 messages

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 2 -s "file-source" persistent://public/default/file-source-topic
    ```

## Adding and consuming messages from the file and a consumer simultaneously

1. Open the file "my-message.txt" from the "resources" directory in the left navigation

1. Start a consumer that runs indefinitly in the terminal

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 0 -s "file-source" persistent://public/default/file-source-topic
    ```

1. Add text to the file and notice the consumer prints the content

1. Add a new line to the file and add more text. The consumer prints the content. You don't need to save the file. It's a continuous stream!

1. Close the consumer by clicking in the terminal area and pressing ctrl+c

## Summary

The premade sources provide a wealth of power "out of the box". You can use sources to complete the change data capture (CDC) pattern, or you can connect to another messageing system to create a continuous stream.

# The End

Congratulations! You can complete the basics of Apache Pulsar lab. Now it's time to extned these concepts into a Pulsar client of your own. Create one in Java, C#, Go, Node, or Python.

---
[Home](./index.md)
