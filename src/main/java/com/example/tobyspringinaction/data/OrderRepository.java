package com.example.tobyspringinaction.data;

import com.example.tobyspringinaction.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;

public class OrderRepository {

    @PersistenceContext
    private EntityManager em;


    public void save(Order order) {
        em.persist(order);
    }
}
