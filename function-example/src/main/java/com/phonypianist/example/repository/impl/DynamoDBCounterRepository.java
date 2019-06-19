package com.phonypianist.example.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.phonypianist.example.repository.CounterRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class DynamoDBCounterRepository implements CounterRepository {

    private AmazonDynamoDB client;

    public DynamoDBCounterRepository(AmazonDynamoDB client) {
        this.client = client;
    }

    @Override
    public Integer countUp(String name) {
        Map<String, AttributeValue> hashKey = Collections.singletonMap(
                "userName", new AttributeValue().withS(name));
        Map<String, AttributeValueUpdate> attributeUpdates = Collections.singletonMap(
                "count", new AttributeValueUpdate().withAction(AttributeAction.ADD).withValue(new AttributeValue().withN("1")));
        UpdateItemRequest updateItemRequest = new UpdateItemRequest("counter", hashKey, attributeUpdates);
        updateItemRequest.setReturnValues("UPDATED_NEW");
        UpdateItemResult updateItemResult = client.updateItem(updateItemRequest);
        String count = updateItemResult.getAttributes().get("count").getN();
        return Integer.valueOf(count);
    }

}
