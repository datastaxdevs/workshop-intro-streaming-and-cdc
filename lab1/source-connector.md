[Home](index.md)

---

## Adding a source connector

In this module we will use the contents of a local file to write messages to a Pulsar topic.

1. Create the connector in Pulsar using the manifest

    ```bash
    ./bin/pulsar-admin sources create \
        --archive ../resources/pulsar-io-file-2.9.2.nar
        --source-config-file ../resources/file-connector.yaml
    ```

1. Start the source connector

    ```bash
    ./bin/pulsar-admin sources status --name "FileSource"
    ```

1. Confirm the source is running

    ```bash
    ./bin/pulsar-admin sources start --name "FileSource"
    ```

1. Add 2 messages to the file

    ```bash
    echo "The first message" > ../resources/my-messages.txt
    echo "The second message" > ../resources/my-messages.txt
    ```

1. Consume the 2 messages

    ```bash
    ./bin/pulsar-client consume -p Earliest -t Shared -n 2 -s "file-source" persistent://public/default/file-source-topic
    ```

## Summary

This was really cool

---
[Next Module](./sink-connector.md)
