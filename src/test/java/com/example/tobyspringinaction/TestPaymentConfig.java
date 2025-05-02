package com.example.tobyspringinaction;

import com.example.tobyspringinaction.payment.ExRateProvider;
import com.example.tobyspringinaction.payment.ExRateProviderStub;
import com.example.tobyspringinaction.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), fixedClock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

    @Bean
    public Clock fixedClock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

}
