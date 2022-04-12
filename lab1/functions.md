[Home](index.md)

---

## Adding a function that augments the message

In this module we will be augmenting the contents of a message before it is saved to the topic and then we will be routing a message to a new topic, based on its text.

1. Create the function in Pulsar using the function config file

    ```bash
    ./bin/pulsar-admin functions create --function-config-file ../resources/augment-function.yaml
    ```

1. Start the function process

    ```bash
    ./bin/pulsar-admin functions start --name ExclamationFunction
    ```

1. Confirm the function is running

    ```bash
    ./bin/pulsar-admin functions status --name ExclamationFunction
    ```

1. Send 2 messages to the function's input topic

    ```bash
    ./bin/pulsar-client produce -m "Hello there" -n 2 persistent://public/default/function-input-topic
    ```

1. Consume the 2 messages and observe the additional "!" on each message

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 2 -s "my-subscription" persistent://public/default/function-output-topic
    ```

## Adding a function that categorizes the message to other topics

In this module we will be forwarding incoming messages from a general "item-purchases" topic to topics specific to the item's cataegory.

1. Create the function in Pulsar using the function config file

    ```bash
    ./bin/pulsar-admin functions create --function-config-file ../resources/filter-function.yaml
    ```

1. Start the function process

    ```bash
    ./bin/pulsar-admin functions start --name FilterFunction
    ```

1. Send a few messages to the function's input topic with different purchase categories

    ```bash
    ./bin/pulsar-client produce -m '{"PurchaseCategory":"Lamp","ItemName":"Some desk lamp"}' -n 1 persistent://public/default/item-purchases

    ./bin/pulsar-client produce -m '{"PurchaseCategory":"Desk","ItemName":"A really cool desk"}' -n 2 persistent://public/default/item-purchases
    ```

1. Consume the "Lamp" category messages

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 1 -s "lamp-subscription" persistent://public/default/lamp-purchase-stream
    ```

1. Consume the "Desk" category messages

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 2 -s "desk-subscription" persistent://public/default/desk-purchase-stream
    ```

## Summary

This was really cool

---
[Next Module](./source-connector.md)