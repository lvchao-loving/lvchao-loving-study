package com.lvchao.redis.test;

import com.lvchao.redis.entity.Lvchao;
import com.lvchao.redis.service.RedisService;
import com.lvchao.redis.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {
    /**
     * 缓存 client的 redis key，这里是 hash结构存储
     */
    private static final String CACHE_CLIENT_KEY = "client_details";

    @Autowired
    private RedisService redisService;

    @Test
    public void test001(){
        Lvchao lvchao = new Lvchao();
        lvchao.setId(100);
        lvchao.setName("吕超");
        lvchao.setAge(23);
        redisService.hset(CACHE_CLIENT_KEY, 100 + "",lvchao);
    }

    @Test
    public void test002(){
        Lvchao lvchao = new Lvchao();
        lvchao.setId(100);
        lvchao.setName("吕超");
        lvchao.setAge(23);
        Lvchao hget = (Lvchao)redisService.hget(CACHE_CLIENT_KEY, 100 + "");
        System.out.println(hget);
    }
    @Test
    public void test003(){
        Lvchao lvchao = new Lvchao();
        lvchao.setId(100);
        lvchao.setName("吕超");
        lvchao.setAge(23);
        redisService.hdel(CACHE_CLIENT_KEY, 100 + "");
    }

}
