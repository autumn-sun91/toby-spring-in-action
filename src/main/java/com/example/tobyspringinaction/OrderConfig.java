package com.example.tobyspringinaction;


import com.example.tobyspringinaction.data.OrderRepository;
import com.example.tobyspringinaction.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Import(DataConfig.class) // DataConfig 의 설정 모두 가져옴
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository();
    }

    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }

}
