package com.example.tobyspringinaction.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExRateExtractor {
    BigDecimal extract(String response) throws JsonProcessingException;
}
