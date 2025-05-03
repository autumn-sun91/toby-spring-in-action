package com.example.tobyspringinaction.payment;

import com.example.tobyspringinaction.TestPaymentConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

// @ContextConfiguration 이용 시 스프링 기능을 확장해달라고 JUnit에게 알려줘야됨
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired
    // BeanFactory beanFactory; // 컨테이너가 가지고 있는 BeanFactory 도 Bean 으로 등록되어서 가져올 수 있음
    PaymentService paymentService;

    @Autowired
    Clock clock;

    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("exRate: 1_000 -> prepare 메소드가 요구사항 3가지(환율 정보 가져오는지, 원환 환산, 원화 환산 유효시간)를 잘 충족했는지 검증")
    void prepare1_000()  {
        // given
        exRateProviderStub.setExRate(BigDecimal.valueOf(1_000));
        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 환율 정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));

        // 원화 환산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));
    }

    @Test
    @DisplayName("exRate: 500 -> prepare 메소드가 요구사항 3가지(환율 정보 가져오는지, 원환 환산, 원화 환산 유효시간)를 잘 충족했는지 검증")
    void prepare500()  {
        // given
        exRateProviderStub.setExRate(BigDecimal.valueOf(500));
        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 환율 정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(500));

        // 원화 환산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }

    @Test
    void validUntil()  {
        // given
        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 원화 환산 유효시간
        // valid until이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);
        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }
}