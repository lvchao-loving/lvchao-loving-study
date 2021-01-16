package com.lvchao.rabbitmq.controller;


import com.lvchao.rabbitmq.producer.RabbitmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lvchao
 * @since 2021-01-14
 */
@RestController
@RequestMapping("/rabbitmq/lc-rabbit-message")
public class LcRabbitMessageController {

    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    @GetMapping("syncSend")
    public String syncSend(Integer num,String exchangeName,String routingKey){
        for (int i = 1; i <= num; i++) {
            rabbitmqProducer.syncSend(i,exchangeName,routingKey);
        }
        return "syncSend success......";
    }
}

