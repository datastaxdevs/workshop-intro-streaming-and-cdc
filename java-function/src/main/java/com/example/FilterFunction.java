package com.example;

import java.util.stream.Collectors;

import com.google.gson.Gson;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;
import org.slf4j.Logger;

public class FilterFunction implements Function<String, Void> {
    @Override
    public Void process(String purchaseMessage, Context context) {
        String inputTopics = context.getInputTopics().stream().collect(Collectors.joining(", "));
        Logger LOG = context.getLogger();

        LOG.info("\nMessage received:\n  Topic: \"{}\"\n  Content: \"{}\"", inputTopics, purchaseMessage);
        
        try {
            // Parse input message
            PurchaseInfo purchaseInfo = PurchaseInfo.fromJson(purchaseMessage);
            
            // Decide filter topic
            switch(purchaseInfo.getPurchaseCategory()){
                case("Lamp"):
                    context.newOutputMessage("lamp-purchase-stream", Schema.STRING).value(purchaseMessage).sendAsync();
                    break;
                case("Desk"):
                    context.newOutputMessage("desk-purchase-stream", Schema.STRING).value(purchaseMessage).sendAsync();
                    break;
                default:
                    LOG.warn(String.format("Unknown purchase category found: \"%s\"", purchaseInfo.getPurchaseCategory()));
            }

            // Ackowledge the message was processed
            context.getCurrentRecord().ack();
        } catch (PulsarClientException e) {
            LOG.error(e.toString());
        }

        return null;
    }
}

class PurchaseInfo
{

    private String PurchaseCategory;
    private String ItemName;

    public static PurchaseInfo fromJson(String purchaseInfo)
    {
        Gson g = new Gson();
        return g.fromJson(purchaseInfo, PurchaseInfo.class);
    }

    public String getPurchaseCategory()
    {
        return this.PurchaseCategory;
    }

    public void setPurchaseCategory(String purchaseCategory)
    {
        this.PurchaseCategory = purchaseCategory;
    }

    public String getItemName()
    {
        return this.ItemName;
    }

    public void setItemName(String itemName)
    {
        this.ItemName = itemName;
    }
}
