package com.phonypianist.example.function;

import com.phonypianist.example.function.dto.EchoRequest;
import com.phonypianist.example.function.dto.EchoResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Component("echoFlux")
public class EchoFluxFunction implements Function<Flux<EchoRequest>, Flux<EchoResponse>> {

    @Override
    public Flux<EchoResponse> apply(Flux<EchoRequest> requestParam) {
        return requestParam.map(param -> new EchoResponse(param.getMessage()));
    }

}
