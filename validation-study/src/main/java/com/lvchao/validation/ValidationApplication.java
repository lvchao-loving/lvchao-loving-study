package com.lvchao.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/24
 */

@SpringBootApplication
@ComponentScan("com.lvchao.validation.mapper")
public class ValidationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValidationApplication.class,args);
    }
}
