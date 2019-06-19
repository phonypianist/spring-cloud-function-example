package com.phonypianist.example.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.model.*;
import com.phonypianist.example.repository.CounterAsyncRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Future;

@Component
public class DynamoDBCounterAsyncRepository implements CounterAsyncRepository {

    private AmazonDynamoDBAsync client;

    public DynamoDBCounterAsyncRepository(AmazonDynamoDBAsync client) {
        this.client = client;
    }

    @Override
    public Mono<Integer> countUp(String name) {
        Map<String, AttributeValue> hashKey = Collections.singletonMap(
                "userName", new AttributeValue().withS(name));
        Map<String, AttributeValueUpdate> attributeUpdates = Collections.singletonMap(
                "count", new AttributeValueUpdate().withAction(AttributeAction.ADD).withValue(new AttributeValue().withN("1")));
        UpdateItemRequest updateItemRequest = new UpdateItemRequest("counter", hashKey, attributeUpdates);
        updateItemRequest.setReturnValues("UPDATED_NEW");
        Future<UpdateItemResult> updateItemResult = client.updateItemAsync(updateItemRequest);
        return Mono.fromCallable(updateItemResult::get)
                .subscribeOn(Schedulers.parallel())
                .map(r -> Integer.valueOf(r.getAttributes().get("count").getN()));
    }
}
