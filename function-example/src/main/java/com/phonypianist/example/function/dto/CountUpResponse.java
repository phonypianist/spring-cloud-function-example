package com.phonypianist.example.function.dto;

public class CountUpResponse {

    private final String name;
    
    private final Integer count;
    
    public CountUpResponse(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

}
