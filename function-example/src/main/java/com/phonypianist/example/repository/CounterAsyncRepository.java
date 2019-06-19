package com.phonypianist.example.repository;

import reactor.core.publisher.Mono;

public interface CounterAsyncRepository {

    Mono<Integer> countUp(String name);

}
