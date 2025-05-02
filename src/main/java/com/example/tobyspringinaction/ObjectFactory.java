package com.example.tobyspringinaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cacheExRateProvider());
    }

    @Bean
    public CacheExRateProvider cacheExRateProvider() {
        return new CacheExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRatePaymentProvider();
    }
}
