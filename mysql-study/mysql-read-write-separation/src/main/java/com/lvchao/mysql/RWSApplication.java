package com.lvchao.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/16
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 设置动态数据源需要，禁用数据源自动配置
@MapperScan("com.lvchao.mysql.mapper")
@EnableTransactionManagement
public class RWSApplication {
    public static void main(String[] args) {
        SpringApplication.run(RWSApplication.class);
    }
}
