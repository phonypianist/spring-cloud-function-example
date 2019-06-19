package com.phonypianist.example.function;

import com.phonypianist.example.function.dto.CountUpRequest;
import com.phonypianist.example.function.dto.CountUpResponse;
import com.phonypianist.example.repository.CounterRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CountUpFunction implements Function<CountUpRequest, CountUpResponse> {

    private CounterRepository counterRepository;

    public CountUpFunction(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public CountUpResponse apply(CountUpRequest requestParam) {
        Integer count = counterRepository.countUp(requestParam.getName());
        return new CountUpResponse(requestParam.getName(), count);
    }

}
