package com.lvchao.redis;

import com.lvchao.redis.annotation.EnableLvchaoLettuceRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/21
 */
@SpringBootApplication
@EnableLvchaoLettuceRedis
public class ResidStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResidStudyApplication.class);
    }
}
