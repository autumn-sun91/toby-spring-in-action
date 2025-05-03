package com.example.tobyspringinaction.exrate;

import com.example.tobyspringinaction.api.ApiTemplate;
import com.example.tobyspringinaction.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRatePaymentProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRatePaymentProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getForExRate(url);
    }
}
