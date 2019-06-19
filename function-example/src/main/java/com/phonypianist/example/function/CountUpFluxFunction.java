package com.phonypianist.example.function;

import com.phonypianist.example.function.dto.CountUpRequest;
import com.phonypianist.example.function.dto.CountUpResponse;
import com.phonypianist.example.repository.CounterAsyncRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component("countUpFlux")
public class CountUpFluxFunction implements Function<Flux<CountUpRequest>, Flux<CountUpResponse>> {

    private CounterAsyncRepository counterRepository;

    public CountUpFluxFunction(CounterAsyncRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public Flux<CountUpResponse> apply(Flux<CountUpRequest> requestParam) {
        return requestParam
                .flatMap(param -> counterRepository.countUp(param.getName()).zipWith(Mono.just(param.getName())))
                .map(count -> new CountUpResponse(count.getT2(), count.getT1()));
    }

}
