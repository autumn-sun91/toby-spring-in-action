package com.example.tobyspringinaction.api;

import com.example.tobyspringinaction.exrate.ExRate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class ErApiExRateExtractor implements ExRateExtractor {

    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRate exRate = mapper.readValue(response, ExRate.class);
        return exRate.rates().get("KRW");
    }
}
