package com.example.tobyspringinaction.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

public class PaymentTest {
    @Test
    void createdPrepared() {
        Clock fixed = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(
                1L,
                "USB",
                BigDecimal.TEN,
                BigDecimal.valueOf(1_000),
                LocalDateTime.now(fixed)
        );

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(fixed).plusMinutes(30));

    }

    @Test
    void isValid() {
        Clock fixed = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(
                1L,
                "USB",
                BigDecimal.TEN,
                BigDecimal.valueOf(1_000),
                LocalDateTime.now(fixed)
        );

        Assertions.assertThat(payment.isValid(fixed)).isTrue();
        Assertions.assertThat(payment.isValid(Clock.offset(fixed, Duration.of(30, ChronoUnit.MINUTES)))).isFalse();
    }
}
