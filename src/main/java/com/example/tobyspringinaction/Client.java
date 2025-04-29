package com.example.tobyspringinaction;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {
//        PaymentService paymentService = new PaymentService(new WebApiExRatePaymentProvider());
        PaymentService paymentService = new PaymentService(new FixExRatePaymentProvider());
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment = " + payment);
    }
}
