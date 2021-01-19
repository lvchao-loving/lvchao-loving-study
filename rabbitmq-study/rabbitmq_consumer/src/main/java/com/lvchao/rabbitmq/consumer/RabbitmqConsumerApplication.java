package com.lvchao.rabbitmq.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/16
 */
@SpringBootApplication
@MapperScan("com.lvchao.rabbitmq.consumer.mapper")
public class RabbitmqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqConsumerApplication.class);
    }
}
