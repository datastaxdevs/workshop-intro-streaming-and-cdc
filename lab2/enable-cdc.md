[Home](index.md)

---

## Enable CDC and initialize the streaming topic

1. Navigate to the database’s "CDC" tab and choose "Enable CDC"

    ![image2](https://user-images.githubusercontent.com/16946028/160469556-940760e0-9ebf-43ae-baba-b18e262a6594.png)

1. Enable CDC with the following details:

    Tenant: `pulsar-gcp-uscentral1 / my-company-streams`
    Keyspace: `our_product`
    Table name: `all_accounts`

    ![image13](https://user-images.githubusercontent.com/16946028/160469581-47f9772d-d624-4495-91e9-39edffb05cea.png)

    Wait for the new CDC process to have a status of "Running"

    ![image6](https://user-images.githubusercontent.com/16946028/160469636-0330efbd-708d-4f0e-bac3-af89cfe3a2c9.png)

1. Navigate back to the database CQL console 

    ![image14](https://user-images.githubusercontent.com/16946028/160469251-eb7bd2eb-c2a9-495c-a2bc-2d57c212316f.png)

1. Add a new record to the all_accounts table, to initialize the streaming objects

    ```
    insert into our_product.all_accounts (id, full_name, email) values (85540e16-aca8-11ec-b909-0242ac120002, 'Joe Smith', 'joesmith@domain.com');
    ```

    ![image3](https://user-images.githubusercontent.com/16946028/160469791-b528c9d4-8c47-4f25-900d-d356ac554c92.png)

## Summary

This was really cool

---
[Next Module](./create-sink.md)
