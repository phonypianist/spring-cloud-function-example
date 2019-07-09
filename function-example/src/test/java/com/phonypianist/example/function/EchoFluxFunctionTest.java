package com.phonypianist.example.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.phonypianist.example.TestConfig;
import com.phonypianist.example.function.dto.EchoRequest;
import com.phonypianist.example.function.dto.EchoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

class EchoFluxFunctionTest {

    @Test
    void echo() {
        EchoFluxFunction echoFluxFunction = new EchoFluxFunction();
        EchoRequest echoRequest = new EchoRequest();
        echoRequest.setMessage("foo");
        Flux<EchoResponse> echoResponse = echoFluxFunction.apply(Flux.just(echoRequest));
        assertThat(echoResponse.toIterable().iterator().next().getMessage()).isEqualTo("foo");
    }

    @Test
    void echoWithSpring() {
        System.setProperty("function.name", "echoFlux");
        try (SpringBootApiGatewayRequestHandler requestHandler = new SpringBootApiGatewayRequestHandler(TestConfig.class)) {
            APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent().withBody("{\"message\":\"foo\"}");
            APIGatewayProxyResponseEvent responseEvent = (APIGatewayProxyResponseEvent) requestHandler.handleRequest(requestEvent, null);
            assertThat(responseEvent.getBody()).isEqualTo("{\"message\":\"foo\"}");
        }
    }

}
