[Home](index.md)

---

## Create a streaming sink

1. Navigate to your organization settings

    ![image15](https://user-images.githubusercontent.com/16946028/160469843-45a812ee-e336-472f-8e42-733b24552f8d.png)

1. Generate a new token with the "Organization Administrator" role

    ![image7](https://user-images.githubusercontent.com/16946028/160470699-808ae7d4-0a1e-47a6-9ea6-a14ab5e18527.png)

1. Click the clipboard ![image4](https://user-images.githubusercontent.com/16946028/160470766-ef040ba0-e437-438f-8e37-be64b72c57c1.png) icon next to the “Token” value to add that string to your clipboard

    ![image5](https://user-images.githubusercontent.com/16946028/160469936-be3c5f31-293a-4050-925e-c8eb4a38c6b5.png)

1. Navigate to my-company-streams "Sinks" page and choose "Create Sink"

    ![image19](https://user-images.githubusercontent.com/16946028/160469996-0acccca4-d6d1-46a9-bb8f-ab765702cba8.png)

1. Create a new sink with the following details:

    The Basics
    - Namespace: `astracdc`
    - Sink Type: Astra DB
    - Name: `astra-account-sink`

    Connect Topics
    - Topic: data-&lt;UUID>-our_product.all_accounts

    Sink-Specific Configuration
    - Database: `my_company`
    - Keyspace: `our_product`
    - Table name: `cdc_accounts`
    - Token: &lt;paste from clipboard>
    - Mapping: `id=key.id, full_name=value.full_name, email=value.email`

    > !! The default "Mapping" value is incorrect for our schema. Make sure to overwrite with the above value.


    ![image1](https://user-images.githubusercontent.com/16946028/160470132-141d5fab-db5e-45c7-b6a9-c1f83f3bbcbe.png)

	Your view will go back to the "Sinks" page where you can see the "Status". Once it says "Running" the sink is complete.

    ![image12](https://user-images.githubusercontent.com/16946028/160470291-41cc67ba-0f60-4d91-bfa8-4838e2b14330.png)

## Summary

This was really cool

---
[Next Module](./observe)
