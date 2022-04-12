[Home](index.md)

---

## Create database, tables, and streaming tenant

1. Choose a region that is supported by CDC for Astra DB by visiting the Streaming documentation's ["Regions" page](https://docs.datastax.com/en/astra-streaming/docs/astream-regions.html) (we'll be using **us-central1** in GCP for this example)

    ![image18](https://user-images.githubusercontent.com/16946028/160468872-d04619f4-8c7f-4315-b673-5d0eb425d47e.png)

1. Login to your Astra account

    ![Screenshot 2022-03-28 152026](https://user-images.githubusercontent.com/16946028/160471085-86edcc64-639f-4395-83b2-9098f2be7d00.png)

1. Choose "Create Database" and create a serverless database with the following details:

    Database Name: `my_company`
    
    Keyspace Name: `our_product`
    
    Provider and Region: `Google Cloud > North America > us-central1`

    ![image17](https://user-images.githubusercontent.com/16946028/160468967-6b54a8a5-2ac9-4798-95eb-5121e6012ee6.png)

1. Once the database has a status of "Active", navigate to its CQL console (you may need to refresh the browser window)

    ![image14](https://user-images.githubusercontent.com/16946028/160469251-eb7bd2eb-c2a9-495c-a2bc-2d57c212316f.png)

1. Create a table that will hold our test account information

    ```
    create table our_product.all_accounts (id uuid primary key, full_name text, email text);
    ```

    ![image16](https://user-images.githubusercontent.com/16946028/160469384-2ee2128e-2666-46fb-bf69-d54da56690b4.png)

1. Create a table that will hold information from the CDC sink

    ```
    create table our_product.cdc_accounts (id uuid primary key, full_name text, email text);
    ```

    ![image10](https://user-images.githubusercontent.com/16946028/160469403-64d0f820-74d7-4d4d-831e-5bd5b5b1a6d5.png)

1. Choose "Create Streaming" and create a new tenant with the following details;

    Tenant Name: `my-company-streams`
    Provider and Region: `Google Cloud > uscentral1`

    ![image9](https://user-images.githubusercontent.com/16946028/160469465-829a24cb-312a-4248-963d-ea3b22116add.png)

The new tenant will be ready to go very quickly and your view will automatically refresh to its “Quickstart” tab. CDC will automatically create a namespace and topic within the tenant.

## Summary

This was really cool

---
[Next Module](./enable-cdc.md)
