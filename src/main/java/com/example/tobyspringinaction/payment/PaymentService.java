package com.example.tobyspringinaction.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class PaymentService {
    private ExRateProvider exRateProvider;
    private Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {

        this.exRateProvider = exRateProvider;
        this.clock = clock;

    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
        // 환율 가져오기
        BigDecimal rate = exRateProvider.getExRate(currency);


        return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, rate, LocalDateTime.now(clock));
    }
}
