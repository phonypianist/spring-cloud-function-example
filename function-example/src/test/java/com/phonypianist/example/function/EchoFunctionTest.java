package com.phonypianist.example.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.phonypianist.example.TestConfig;
import com.phonypianist.example.function.dto.EchoRequest;
import com.phonypianist.example.function.dto.EchoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler;

import static org.assertj.core.api.Assertions.assertThat;

class EchoFunctionTest {

    @Test
    void echo() {
        EchoFunction echoFunction = new EchoFunction();
        EchoRequest echoRequest = new EchoRequest();
        echoRequest.setMessage("foo");
        EchoResponse echoResponse = echoFunction.apply(echoRequest);
        assertThat(echoResponse.getMessage()).isEqualTo("foo");
    }

    @Test
    void echoWithSpring() {
        System.setProperty("function.name", "echo");
        try (SpringBootApiGatewayRequestHandler requestHandler = new SpringBootApiGatewayRequestHandler(TestConfig.class)) {
            APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent().withBody("{\"message\":\"foo\"}");
            APIGatewayProxyResponseEvent responseEvent = (APIGatewayProxyResponseEvent) requestHandler.handleRequest(requestEvent, null);
            assertThat(responseEvent.getBody()).isEqualTo("{\"message\":\"foo\"}");
        }
    }

}
