package com.example.tobyspringinaction.data;

import com.example.tobyspringinaction.order.Order;
import com.example.tobyspringinaction.order.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

public class JdbcOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    // 빈 초기화 이후 컨테이너가 자동으로 실행하는 애노테이션
    @PostConstruct
    void init() {
        jdbcClient.sql("""
                create table orders(id    bigint not null,no    varchar(255),total numeric(38, 2),primary key (id));
                alter table if exists orders drop constraint if exists orders_no_unique;
                alter table if exists orders add constraint orders_no_unique unique (no);
                create sequence orders_SEQ start with 1 increment by 50;
                """).update();
    }

    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select next value for orders_SEQ").query(Long.class).single();
        order.setId(id);

        jdbcClient.sql("insert into orders (no, total, id) values (?, ?, ?);")
                .params(order.getNo(), order.getTotal(), order.getId())
                .update();
        System.out.println("id = " + id);
    }
}
