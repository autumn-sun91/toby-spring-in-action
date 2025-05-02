package com.example.tobyspringinaction;

import com.example.tobyspringinaction.exrate.WebApiExRatePaymentProvider;
import com.example.tobyspringinaction.payment.ExRateProvider;
import com.example.tobyspringinaction.payment.ExRateProviderStub;
import com.example.tobyspringinaction.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

}
