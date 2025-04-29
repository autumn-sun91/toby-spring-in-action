package com.example.tobyspringinaction;

import java.io.IOException;
import java.math.BigDecimal;

public class FixExRatePaymentProvider implements ExRateProvider{

    @Override
    public BigDecimal getExRate(String currency) throws IOException {

        if (currency.equals("USD")) {
            return BigDecimal.valueOf(1_000);
        }

        throw new IllegalArgumentException("Only USD");
    }
}
