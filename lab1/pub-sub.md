[Home](index.md)

---

## Pub/Sub

We are going to send a message to the broker and consume that message from the broker. This is commonly referred to the publish and subscribe model. To keep things simple we will not be creating a subscriber, instead will do a simple consume.

To complete Pub/Sub you will need a tenant and namespace for publishing and consuming messages. Pulsar creates defaults just for such a demonstration. We will be using the default values:

Tenant: `default`

Namespace: `public`

1. Send 2 messages to the topic `my-first-topic`

    Otherwise known as producing a message. Pulsar will create a new topic named "my-first-topic".

    ```bash
    ./bin/pulsar-client produce -m "Hello there" -n 2 persistent://public/default/my-first-topic
    ```

    There will be quite a bit of feedback output to the screen. Upon a successful publish, the last line should be…

    ```log
    ...
    [main] INFO org.apache.pulsar.client.cli.PulsarClientTool - 2 messages successfully produced
    ```

1. Get the first message from the Broker

    Otherwise known as consuming messages. Similar to producing, if the topic does not exist Pulsar will create it.

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -s "my-subscription" persistent://public/default/my-first-topic
    ```

    There will be quite a bit of feedback output to the screen. Look for the line `----- got message -----` and the text below that should contain...

    ```log
    key:[null], properties:[], content:Hello world
    ...
    [main] INFO org.apache.pulsar.client.cli.PulsarClientTool - 1 messages successfully consumed
    ```

1. Get the rest of the messages from the Broker

    We have only consumed (otherwise known as acknowledged) the first message on the topic. There is still another waiting. Use the following command to retrieve and acknowledged the last 'N' messages.

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 1 -s "my-subscription" persistent://public/default/my-first-topic
    ```

    The output will be similar to the previous step.

## Summary

This was really cool

---
[Next Module](./functions.md)