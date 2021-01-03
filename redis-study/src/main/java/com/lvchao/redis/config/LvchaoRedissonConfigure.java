package com.lvchao.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/29
 */
@Configuration
public class LvchaoRedissonConfigure {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient getClient(){
        // 使用单机模式
        Config config = new Config();
        System.out.println("redisHost:" + redisHost);
        System.out.println("port:" + port);
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + port).setDatabase(0);
        return Redisson.create(config);
    }
}
