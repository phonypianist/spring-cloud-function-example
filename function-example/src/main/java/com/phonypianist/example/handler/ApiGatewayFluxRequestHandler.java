package com.phonypianist.example.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler;

import java.util.List;

public class ApiGatewayFluxRequestHandler extends SpringBootApiGatewayRequestHandler {

    protected Class<?> getInputType() {
        return List.class;
    }

}
