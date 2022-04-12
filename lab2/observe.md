[Home](index.md)

---

## Insert data to observe CDC in action

1. Navigate back to the database CQL console 

    ![image14](https://user-images.githubusercontent.com/16946028/160469251-eb7bd2eb-c2a9-495c-a2bc-2d57c212316f.png)

1. Add a new record to the all_accounts table

    `insert into our_product.all_accounts (id, full_name, email) values (5b6962dd-3f90-4c93-8f61-eabfa4a803e2, 'Suzie Shoe', 'suzie_s@acoolplace.com');`

1. Observe the new record in cdc_accounts table by selecting all rows

    `select * from our_product.cdc_accounts;`

    ![image11](https://user-images.githubusercontent.com/16946028/160470454-7b143f63-f59f-4385-9028-013ea796923b.png)

## Summary

This was really cool

---
[Home](./index.md)
