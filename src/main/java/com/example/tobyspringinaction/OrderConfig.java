package com.example.tobyspringinaction;


import com.example.tobyspringinaction.data.JdbcOrderRepository;
import com.example.tobyspringinaction.order.OrderRepository;
import com.example.tobyspringinaction.order.OrderService;
import com.example.tobyspringinaction.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class) // DataConfig 의 설정 모두 가져옴
@EnableTransactionManagement
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }

}
