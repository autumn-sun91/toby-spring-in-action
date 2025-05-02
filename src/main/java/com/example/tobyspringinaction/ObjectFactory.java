package com.example.tobyspringinaction;

import com.example.tobyspringinaction.exrate.CacheExRateProvider;
import com.example.tobyspringinaction.payment.ExRateProvider;
import com.example.tobyspringinaction.exrate.WebApiExRatePaymentProvider;
import com.example.tobyspringinaction.payment.PaymentService;
import org.springframework.context.annotation.Bean;
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
