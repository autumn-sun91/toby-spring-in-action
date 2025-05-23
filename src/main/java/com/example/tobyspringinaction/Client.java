package com.example.tobyspringinaction;

import com.example.tobyspringinaction.payment.Payment;
import com.example.tobyspringinaction.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args)  {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment1 = " + payment);
    }
}
