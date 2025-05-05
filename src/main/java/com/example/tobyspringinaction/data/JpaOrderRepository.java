package com.example.tobyspringinaction.data;

import com.example.tobyspringinaction.order.Order;
import com.example.tobyspringinaction.order.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }
}
