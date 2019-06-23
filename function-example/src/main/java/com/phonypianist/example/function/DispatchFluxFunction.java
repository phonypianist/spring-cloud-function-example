package com.phonypianist.example.function;

import com.phonypianist.example.function.dto.EchoRequest;
import com.phonypianist.example.function.dto.EchoResponse;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Component("dispatchFlux")
public class DispatchFluxFunction implements Function<Flux<EchoRequest>, Flux<EchoResponse>> {

    private final WebClient webClient;

    private final Environment environment;

    public DispatchFluxFunction(WebClient webClient, Environment environment) {
        this.webClient = webClient;
        this.environment = environment;
    }

    @Override
    public Flux<EchoResponse> apply(Flux<EchoRequest> requestParam) {
        String uri = environment.getProperty("app.echoFlux.uri");
        return requestParam.flatMap(param -> webClient
                .post()
                .uri(uri)
                .body(BodyInserters.fromObject(param))
                .retrieve().bodyToMono(EchoResponse.class));
    }

}
