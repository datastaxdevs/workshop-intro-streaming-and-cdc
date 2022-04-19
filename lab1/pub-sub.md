[Home](index.md)

---

## Pub/Sub

We are going to send a message to the Broker and consume that message from the broker. This is commonly referred as the publish/subscribe model. To keep things simple we will not be creating an observer, instead will do a simple consume. Creating an observer means to openly watch for new messages on a topic. Typically that is the pattern used when an application uses messaging.

1. Send 2 messages to the topic `my-first-topic`

    Otherwise known as producing a message. Pulsar will automatically create a new topic named "my-first-topic" and send 2 messages to the topic both with the same content of "Hello there".

    ```bash
    ./bin/pulsar-client produce -m "Hello there" -n 2 persistent://public/default/my-first-topic
    ```

    There will be quite a bit of feedback on the screen becuase verbose logging is turned on. Upon a successful publish, the last line should be…

    ```log
    
    ...
    [main] INFO org.apache.pulsar.client.cli.PulsarClientTool - 2 messages successfully produced
    ```

1. Get the first message from the Broker

    Otherwise known as consuming messages. Similar to producing, if the topic does not exist Pulsar will create it.

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 1 -s "my-subscription" persistent://public/default/my-first-topic
    ```

    There will be quite a bit of feedback output to the screen. Look for the line `----- got message -----` and the text below that should contain...

    ```log
    key:[null], properties:[], content:Hello world

    ...

    [main] INFO org.apache.pulsar.client.cli.PulsarClientTool - 1 messages successfully consumed
    ```

1. Get the rest of the messages from the Broker

    We have only consumed (otherwise known as acknowledged) the first message on the topic. There is still another waiting. Use the following command to retrieve and acknowledged the last message.

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 1 -s "my-subscription" persistent://public/default/my-first-topic
    ```

    The output will be similar to the previous step.

## Summary

This example was a very controlled way of producing and consuming messages from a Pulsar Broker. Normally you wouldn't know how many messages are available for consumption. Instead you would choose "earliest" which is instruction to get messages that have not been acknowledged, or "latest" where you want to get new messages as they are added to the topic. Having this much control over how a consumer "views" the queue of messages is a unique feature to Pulsar. If the messages are persisted, a consumer can choose a position in the queue to start at. Think of that as a playlist of messages that consumers can come and go on.

Another convenient feature is the type of subscription that is created for consuming. It could be "shared" or "exclusive" or others. The value represents if other consumes can share the same subscription's configuration and messages, or if it is exclusive and not open.

Now that we have the basics of producing and consuming messages, let's add a function in the middle!

---
[Next Module >>](./functions.md)