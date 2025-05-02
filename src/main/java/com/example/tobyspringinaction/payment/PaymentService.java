package com.example.tobyspringinaction.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {
    private ExRateProvider exRateProvider;

    public PaymentService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 환율 가져오기
        BigDecimal rate = exRateProvider.getExRate(currency);

        // 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(rate);

        // 유효 시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, convertedAmount, rate, validUntil);
    }
}
