package com.phonypianist.example;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@TestConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.phonypianist.example",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Application.class))
public class TestConfig {

    @Bean
    AmazonDynamoDBAsync amazonDynamoDBAsync() {
        return Mockito.mock(AmazonDynamoDBAsync.class);
    }

}
