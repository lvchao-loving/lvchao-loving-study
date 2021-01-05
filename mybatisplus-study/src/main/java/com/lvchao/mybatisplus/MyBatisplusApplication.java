package com.lvchao.mybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/4
 */
@SpringBootApplication
@ComponentScan("com.lvchao.mybatisplus.mapper")
public class MyBatisplusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisplusApplication.class);
    }
}
