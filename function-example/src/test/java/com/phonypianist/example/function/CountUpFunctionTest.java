package com.phonypianist.example.function;

import static org.assertj.core.api.Assertions.assertThat;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.phonypianist.example.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.phonypianist.example.Application;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class CountUpFunctionTest {

    @Test
    void countUp() {
        SpringBootRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> requestHandler = new SpringBootApiGatewayRequestHandler(TestConfig.class);
        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent().withBody("{\"name\":\"foo\"}");
        APIGatewayProxyResponseEvent responseEvent = (APIGatewayProxyResponseEvent)requestHandler.handleRequest(requestEvent, null);
    }

}
