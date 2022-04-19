[Next Module >>](/lab1/pub-sub.md)

---

## Getting familiar with the Pulsar broker

Running Apache Pulsar's broker and interacting with it's features include a few different binaries. To run the broker the `pulsar` and `pulsar-daemon` binaries are used. To configure and interact with the broker the `pulsar-admin` binary is used.

This GitPod has preloaded a compatible Java Runtime, downloaded and installed Pulsar's binaries, and started the broker in "standalone" mode. Which means all necessary components are run along-side the broker (ie: bookkeeper, zookeeper, etc).

A new terminal window should be open named "Working-Directory". This is the terminal you will use for this lab. All commands provided in the lab can be copy/paste to this terminal window.

Lets test out the terminal window and copy/paste...

1. List preloaded tenants

    ```bash
    ./bin/pulsar-admin tenants list
    ```

    Output:

    ```logs
    "public"
    "pulsar"
    "sample"
    ```

    Using the default configurations for Pulsar, a default tenant named "public" is automatically created. A tenant is a logical area dedicated (ie: secured) for a specific use. Think of tenants in terms of a product or line of business (although it could be as granular as you like). We wil be using this tenant name throughout the lab.

1. List preloaded namespaces within the public tenant

    ```bash
    ./bin/pulsar-admin namespaces list "public"
    ```

    Output:

    ```logs
    "public/default"
    "public/functions"
    ```

    Within the "public" tenant 2 namespaces were also automatically created named "default" and "functions". Think if namespaces within a tenant as a logical area for a specifc environment. They could be "development", "test", "production" or they could be "datacenter-1", "datacenter-2". It's up to you to decide what these logical seperations represent. We will be using the "default" namespace throughout the lab.

1. List topics within the public/default namespace

    ```bash
    ./bin/pulsar-admin topics list "public/default"
    ```

    The result of this query should be blank. There are no topics loaded in the public/default namespace. A topic is where messages are held. Decisions like if the messages should be persisted or non-persisted, the message retention time, and other configurations are provided when creating the topic.

## Summary

Tenants, namespaces, and topics are the basics of Pulsar. These things will be represented inm every interaction you have with the Broker. Notice each command used the `bin` folder to execute a binary. This folder is where all binaries are stored. Let's continue to the next module where we will add and consume messages on a topic.

Scroll to the top to navigate to the next module.
