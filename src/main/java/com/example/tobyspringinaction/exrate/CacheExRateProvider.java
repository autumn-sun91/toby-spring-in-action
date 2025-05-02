package com.example.tobyspringinaction.exrate;

import com.example.tobyspringinaction.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CacheExRateProvider implements ExRateProvider {
    private final ExRateProvider target;

    private BigDecimal cachedExRate;

    private LocalDateTime cacheExpiryTime;

    public CacheExRateProvider(ExRateProvider exRateProvider) {
        this.target = exRateProvider;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if (cachedExRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExRate = this.target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("[ExRateProvider::CacheExRateProvider] cache update = " + cachedExRate);
        }
        return cachedExRate;
    }
}
