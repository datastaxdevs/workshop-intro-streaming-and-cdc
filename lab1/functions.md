[Home](index.md)

---

## Adding a function that augments the message

In this module we will be augmenting the contents of a message that is saved to a certain topic, and routing the new message to a new topic. The functionw as written in Java. You could also creat it in Go or Python. Refer to "java-function" folder in the left navigation to review how a function is created.

1. Create the function in Pulsar using the provided function config file

    Looking at the config yaml, notice the "inputs" and "output" values. The same function can "watch" multiple topics. Also notice the value "parallelism". This instructs the Broker to open N number of threads running the function. If the topic had high load, one running function would be a bottleneck. Pulsar can run many instances to disctibute the load and take processing time (ie: latency) to almost zero.

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

    As the Broker receives these messages, the function will be run.

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

In this module we will use a function to parse an incoming message (as JSON) and foward the message to a different topic based on the "PurchaseCategory" value.

1. Create the function in Pulsar using the function config file

    Notice in this config there is not "output" value. That is becuase this function will internally forward the message to a specfic topic. Normally the topic names to forward to would not be hard coded in the function. Instead they would be provided as dynamic configuration parameters when the function is created.

    ```bash
    ./bin/pulsar-admin functions create --function-config-file ../resources/filter-function.yaml
    ```

1. Send a few messages to the function's input topic with different purchase categories

    ```bash
    ./bin/pulsar-client produce -s ";" -m "{\"PurchaseCategory\":\"Lamp\",\"ItemName\":\"Some desk lamp\"}" -n 1 persistent://public/default/item-purchases
    ```
    ```bash
    ./bin/pulsar-client produce -s ";" -m "{\"PurchaseCategory\":\"Desk\",\"ItemName\":\"A really cool desk\"}" -n 1 persistent://public/default/item-purchases
    ```

    Notice the additional `-s` parameter. The default for the produce command is to parse multiple messages delimited by a comma ','. But we used the comma in well formed json. So the delimiter was switched to be a semi-colon ';'.

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

If you changed the message to be malformed json (ie: remove the last "`}`") and then "produced" the message, it would not process successfully. The exception would be available in a few places...

- The `latestUserExceptions` property of the function's status
- The function logs located at `logs/functions/public/default/FilterFunction/FilterFunction-0.log`
- A logging topic that was specificed in the config yaml

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 1 -s "filter-function-subscription" persistent://public/default/filter-function-logs
    ```

## Summary

Functions are a very powerful feature of Pulsar. You could use them to make real time decisions with machine learning or you could use them to distribute messages to different teams. The possabilites are endless with functions!

To this point we have been publishing messages manually to topics. Next let's have Pulsar watch a "source" for messages.

---
[Next Module >>](./source-connector.md)