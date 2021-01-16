package com.lvchao.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/14
 */
@SpringBootApplication
@MapperScan("com.lvchao.rabbitmq.mapper")
public class RabbitmqProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class);
    }
}
