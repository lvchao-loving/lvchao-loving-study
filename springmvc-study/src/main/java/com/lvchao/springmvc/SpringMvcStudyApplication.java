package com.lvchao.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/19
 */

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.lvchao.springmvc.listener"})
public class SpringMvcStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMvcStudyApplication.class);
    }
}
