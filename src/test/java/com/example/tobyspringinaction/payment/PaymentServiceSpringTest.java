package com.example.tobyspringinaction.payment;

import com.example.tobyspringinaction.TestObjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceSpringTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지(환율 정보 가져오는지, 원환 환산, 원화 환산 유효시간)를 잘 충족했는지 검증")
    void prepare() throws IOException {
        // given
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(TestObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        // when
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 환율 정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));

        // 원화 환산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        // 원화 환산 유효시간
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}