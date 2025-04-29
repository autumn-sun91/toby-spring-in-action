package com.example.tobyspringinaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {
    private FixExRatePaymentProvider exRateProvider;

    public PaymentService() {
        this.exRateProvider = new FixExRatePaymentProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 환율 가져오기
        BigDecimal rate = exRateProvider.getBigDecimal(currency);

        // 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(rate);

        // 유효 시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, convertedAmount, rate, validUntil);
    }
}
