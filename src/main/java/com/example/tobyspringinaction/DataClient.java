package com.example.tobyspringinaction;

import com.example.tobyspringinaction.order.Order;
import com.example.tobyspringinaction.payment.PaymentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OrderBy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        // em 생성
        EntityManager em = emf.createEntityManager();

        // transaction 생성
        em.getTransaction().begin();

        // em.persist -> Order 저장
        Order order = new Order("100", BigDecimal.TEN);
        em.persist(order);

        System.out.println("order = " + order);

        em.getTransaction().commit();
        em.close();
    }
}
