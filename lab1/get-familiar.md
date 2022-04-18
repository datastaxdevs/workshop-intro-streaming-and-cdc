[Home](index.md)

---

## Installing binaries and running a Pulsar broker

1. To confirm you have a valid Java runtime open a terminal window and run the following command. To install the Java Runtime Environment (JRE) follow the installation wizard [here](https://adoptopenjdk.net/installation.html). NOTE when choosing your platform, the name should include `x64 jre`.

    ```bash
    java --version
    ```

    The version of java returned should be >= 8 and it should be 64-Bit.

1. Within the terminal create a new directory that we will run the lab from and change into that directory

    ```bash
    mkdir working-with-pulsar
    cd working-with-pulsar
    ```

1. Download the Pulsar binaries artifact into the new directory, expand the artifact, and change into that directory

    > Note this download is ~325mb, please be patient

    ```bash
    curl -O https://archive.apache.org/dist/pulsar/pulsar-2.9.2/apache-pulsar-2.9.2-bin.tar.gz
    tar xvfz ./apache-pulsar-2.9.2-bin.tar.gz
    cd ./apache-pulsar-2.9.2
    ```

1. Start a Pulsar broker in a background thread

    ```bash
    ./bin/pulsar-daemon start standalone
    ```

    As the broker starts you will see a similar output to:

    ```log
    doing start standalone ...
    starting standalone, logging to /xxxx/logs/pulsar-standalone-xxxxx.log
    Note: Set immediateFlush to true in conf/log4j2.yaml will guarantee the logging event is flushing to disk immediately. The default behavior is switched off due to performance considerations.
    ```

1. Confirm your connection with the Broker

    ```bash
    ./bin/pulsar-admin namespaces list public
    ```

    Output:

    ```logs
    "public/default"
    "public/functions"
    ```

## Summary

This was really cool

---
[Next Module](./pub-sub.md)