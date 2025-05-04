package com.example.tobyspringinaction;

import com.example.tobyspringinaction.api.ApiTemplate;
import com.example.tobyspringinaction.api.ErApiExRateExtractor;
import com.example.tobyspringinaction.api.SimpleApiExecutor;
import com.example.tobyspringinaction.exrate.RestTemplateExRateProvider;
import com.example.tobyspringinaction.exrate.WebApiExRatePaymentProvider;
import com.example.tobyspringinaction.payment.ExRateProvider;
import com.example.tobyspringinaction.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
