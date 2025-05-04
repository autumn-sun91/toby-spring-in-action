package com.example.tobyspringinaction.order;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "orders_no_unique",
                        columnNames = {"no"}
                )})
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String no;

    @Column
    private BigDecimal total;

    protected Order() {
    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
