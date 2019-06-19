package com.phonypianist.example.function;

import com.phonypianist.example.function.dto.CountUpRequest;
import com.phonypianist.example.function.dto.CountUpResponse;
import com.phonypianist.example.repository.CounterRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Component("countUpFlux")
public class CountUpFluxFunction implements Function<Flux<CountUpRequest>, Flux<CountUpResponse>> {

    private CounterRepository counterRepository;

    public CountUpFluxFunction(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public Flux<CountUpResponse> apply(Flux<CountUpRequest> requestParam) {
        return requestParam.map(param -> {
            Integer count = counterRepository.countUp(param.getName());
            return new CountUpResponse(param.getName(), count);
        });
    }

}
