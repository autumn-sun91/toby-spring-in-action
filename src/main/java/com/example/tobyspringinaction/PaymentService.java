package com.example.tobyspringinaction;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 환율 가져오기
        BigDecimal rate = getBigDecimal(currency);

        // 금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(rate);

        // 유효 시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, convertedAmount, rate, validUntil);
    }

    private static BigDecimal getBigDecimal(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String collect = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();

        ExRate exRate = mapper.readValue(collect, ExRate.class);

        BigDecimal rate = exRate.rates().get("KRW");
        return rate;
    }

    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment = " + payment);
    }
}
