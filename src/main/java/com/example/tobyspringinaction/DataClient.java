package com.example.tobyspringinaction;

import com.example.tobyspringinaction.data.OrderRepository;
import com.example.tobyspringinaction.order.Order;
import com.example.tobyspringinaction.payment.PaymentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OrderBy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            // transaction begin
            new TransactionTemplate(transactionManager).execute(status -> {
                Order order = new Order("100", BigDecimal.TEN);
                orderRepository.save(order);

                System.out.println("order = " + order);

                Order order2 = new Order("100", BigDecimal.ONE);
                orderRepository.save(order2);
                return null;
            });
            // commit
        } catch (DataIntegrityViolationException e) {
            System.out.println("주문 번호 중복 복구 작업");
        }
    }
}
