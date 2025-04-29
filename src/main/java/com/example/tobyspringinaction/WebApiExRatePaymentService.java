package com.example.tobyspringinaction;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class WebApiExRatePaymentService extends PaymentService{
    @Override
    BigDecimal getBigDecimal(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String collect = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();

        ExRate exRate = mapper.readValue(collect, ExRate.class);

        return exRate.rates().get("KRW");
    }
}
