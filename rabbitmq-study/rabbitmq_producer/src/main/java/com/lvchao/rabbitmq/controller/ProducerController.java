package com.lvchao.rabbitmq.controller;

import com.lvchao.rabbitmq.producer.RabbitmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/14
 */
@RestController
@RequestMapping("rabbitmq/producer")
public class ProducerController {

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
