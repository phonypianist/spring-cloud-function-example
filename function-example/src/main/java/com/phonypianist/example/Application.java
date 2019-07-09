package com.phonypianist.example;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.phonypianist.example.function.EchoFunction;
import com.phonypianist.example.function.dto.EchoRequest;
import com.phonypianist.example.function.dto.EchoResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Function;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        //FunctionalSpringApplication.run(Application.class, args);
        FunctionalSpringApplication application = new FunctionalSpringApplication(Application.class);
        application.setApplicationContextClass(null);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }

    /*@Bean
    AmazonDynamoDBAsync amazonDynamoDBAsync() {
        return AmazonDynamoDBAsyncClientBuilder.standard().build();
    }*/

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }

}
