package com.phonypianist.example.function;

import com.phonypianist.example.function.dto.EchoRequest;
import com.phonypianist.example.function.dto.EchoResponse;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Component("dispatch")
public class DispatchFunction implements Function<EchoRequest, EchoResponse> {

    private RestTemplate restTemplate;

    private Environment environment;

    public DispatchFunction(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    @Override
    public EchoResponse apply(EchoRequest requestParam) {
        String uri = environment.getProperty("ECHO_URI");
        return restTemplate.postForObject(uri, requestParam, EchoResponse.class);
    }

}
