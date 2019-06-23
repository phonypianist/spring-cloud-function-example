package com.phonypianist.example;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@TestConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.phonypianist.example",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Application.class))
public class TestConfig {

    @Bean
    AmazonDynamoDBAsync amazonDynamoDBAsync() {
        return Mockito.mock(AmazonDynamoDBAsync.class);
    }

    @Bean
    RestTemplate restTemplate() {
        return new TestRestTemplate().getRestTemplate();
    }

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }

}
