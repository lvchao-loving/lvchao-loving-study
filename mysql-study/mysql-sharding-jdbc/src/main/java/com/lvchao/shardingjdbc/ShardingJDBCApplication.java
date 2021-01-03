package com.lvchao.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/17
 */

@SpringBootApplication
@MapperScan("com.lvchao.shardingjdbc.mapper")
public class ShardingJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJDBCApplication.class);
    }
}
