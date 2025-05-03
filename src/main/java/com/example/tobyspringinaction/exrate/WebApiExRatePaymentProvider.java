package com.example.tobyspringinaction.exrate;

import com.example.tobyspringinaction.api.ApiTemplate;
import com.example.tobyspringinaction.api.ErApiExRateExtractor;
import com.example.tobyspringinaction.api.HttpClientApiExecutor;
import com.example.tobyspringinaction.payment.ExRateProvider;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebApiExRatePaymentProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate(); // 상태값 없으므로 한번만 생성

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}
