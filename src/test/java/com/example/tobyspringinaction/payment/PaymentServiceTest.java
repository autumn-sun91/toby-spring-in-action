package com.example.tobyspringinaction.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지(환율 정보 가져오는지, 원환 환산, 원화 환산 유효시간)를 잘 충족했는지 검증")
    void prepare() throws IOException {
        testAmount(valueOf(500), valueOf(5_000), this.clock);
        testAmount(valueOf(1_000), valueOf(10_000), this.clock);
        testAmount(valueOf(3_000), valueOf(30_000), this.clock);
    }

    @Test
    void validUntil() throws IOException {
        // given
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);

        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 원화 환산 유효시간
        // valid until이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);
        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    @NonNull
    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
        // given
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 환율 정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화 환산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}