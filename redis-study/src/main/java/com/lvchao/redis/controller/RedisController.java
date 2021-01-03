package com.lvchao.redis.controller;

import com.lvchao.redis.service.RedisService;
import com.lvchao.redis.utils.SleepTools;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/21
 */
@RestController
// @RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @Value("${server.port}")
    private String host;

    @Autowired
    private RedissonClient redissonClient;

    private String redisLockKey = "lvchao1111";

    @GetMapping("setRedis")
    public Boolean setRedis(String key,Integer value){
      //  String key = "lvchao" + new Random().nextInt(10);
        return redisService.set(key, value);
    }

    @GetMapping("incRedis")
    public String incRedis(String key){
        Integer num = (Integer) redisService.get(key);
        if (num > 0){
            redisService.incr(key,1L);
            //redisService.set(key, num - 1);
        }else{
            System.out.println("库存已减为0，不能再减了。当前redis数量为：" + redisService.get(key));
        }
        return key;
    }

    @GetMapping("decRedis")
    public String decRedis(String key){
        RLock lock = redissonClient.getLock(redisLockKey);
        try{
            lock.lock();
            Integer num = (Integer) redisService.get(key);
            if (num != null && num > 0){
                SleepTools.milli(new Random().nextInt(500));
                System.out.println("端口"+host+"消费，当前数量为：" + num);
                redisService.decr(key,1L);
                // redisService.set(key, num - 1);
            }else{
                System.out.println("库存已减为0，不能再减了。当前redis数量为：" + redisService.get(key));
            }
        }finally {
            lock.unlock();
        }
        return key;
    }

    @GetMapping("delRedis")
    public String delRedis(String key){
        redisService.del(key);
        return key;
    }
}
