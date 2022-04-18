[Home](index.md)

---

## Adding a function that augments the message

In this module we will be augmenting the contents of a message before it is saved to the topic and then we will be routing a message to a new topic, based on its text.

1. Create the function in Pulsar using the function config file

    ```bash
    ./bin/pulsar-admin functions create --function-config-file ../resources/exclamation-function.yaml
    ```

1. Confirm the function is running

    ```bash
    ./bin/pulsar-admin functions status --name ExclamationFunction
    ```

    The output will give you quite a bit of information about the function. Pay attention to the `running` value, it should be `true`.

    ```yaml
    {
        "numInstances" : 1,
        "numRunning" : 1,
        "instances" : [{
            "instanceId" : 0,
            "status" : {
                "running" : true,
                "error" : "",
                "numRestarts" : 0,
                "numReceived" : 0,
                "numSuccessfullyProcessed" : 0,
                "numUserExceptions" : 0,
                "latestUserExceptions" : [ ],
                "numSystemExceptions" : 0,
                "latestSystemExceptions" : [ ],
                "averageLatency" : 0.0,
                "lastInvocationTime" : 0,
                "workerId" : "c-standalone-fw-localhost-8080"
            }
        }]
    }
    ```

1. Send 2 messages to the function's input topic

    ```bash
    ./bin/pulsar-client produce -m "Hello there" -n 2 persistent://public/default/exclamation-input-topic
    ```

1. Consume the 2 messages and observe the additional "!" on each message

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 2 -s "my-subscription" persistent://public/default/exclamation-output-topic
    ```

    The output will show the messages were augmented with a trailing exclamation: "Hello there!"

    ```log
    ...

    ----- got message -----
    key:[null], properties:[__pfn_input_msg_id__=CBAQACAAMAE=, __pfn_input_topic__=persistent://public/default/exclamation-input-topic], content:Hello there!
    ----- got message -----
    key:[null], properties:[__pfn_input_msg_id__=CBAQASAAMAE=, __pfn_input_topic__=persistent://public/default/exclamation-input-topic], content:Hello there!

    ...
    ```

## Adding a function that categorizes the message to other topics

In this module we will be forwarding incoming messages from a general "item-purchases" topic to topics specific to the item's cataegory.

1. Create the function in Pulsar using the function config file

    ```bash
    ./bin/pulsar-admin functions create --function-config-file ../resources/filter-function.yaml
    ```

1. Send a few messages to the function's input topic with different purchase categories

    ```bash
    ./bin/pulsar-client produce -m "{\"PurchaseCategory\":\"Lamp\",\"ItemName\":\"Some desk lamp\"}" -n 1 persistent://public/default/item-purchases
    ```
    ```bash
    ./bin/pulsar-client produce -m '{"PurchaseCategory":"Desk","ItemName":"A really cool desk"}' -n 1 persistent://public/default/item-purchases
    ```

1. Consume the "Lamp" category messages

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 1 -s "lamp-subscription" persistent://public/default/lamp-purchase-stream
    ```

1. Consume the "Desk" category messages

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 1 -s "desk-subscription" persistent://public/default/desk-purchase-stream
    ```

1. View the status of the function to confirm no exceptions happened

    ```bash
    ./bin/pulsar-admin functions status --name FilterFunction
    ```

1. View the function's logs

    If you changed the message to be malformed json (ie: remove the last "`}`") and then "produced" the message, it would not process successfully. The exception would be available in a few places...

    - The `latestUserExceptions` property of the function's status
    - The function logs located at `logs/functions/public/default/FilterFunction/FilterFunction-0.log`
    - An logging topic that was specificed when the log was created

        ```bash
        ./bin/pulsar-client consume -p Earliest -t Shared -n 1 -s "filter-function-subscription" persistent://public/default/filter-function-logs
        ```

## Summary

This was really cool

---
[Next Module](./source-connector.md)