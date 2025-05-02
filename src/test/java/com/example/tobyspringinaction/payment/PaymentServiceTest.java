package com.example.tobyspringinaction.payment;

import com.example.tobyspringinaction.exrate.WebApiExRatePaymentProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void prepare() throws IOException {
        // given
        PaymentService paymentService = new PaymentService(new WebApiExRatePaymentProvider());

        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 환율 정보 가져온다
        assertThat(payment.getExRate()).isNotNull();

        // 원화 환산
        assertThat(payment.getConvertedAmount())
                .isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 원화 환산 유효시간
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}