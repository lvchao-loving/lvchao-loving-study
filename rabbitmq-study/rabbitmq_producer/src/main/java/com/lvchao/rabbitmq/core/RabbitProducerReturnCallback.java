package com.lvchao.rabbitmq.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitProducerReturnCallback implements RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        synchronized (this){
            System.out.println();
            System.out.println("RabbitTemplate.ReturnCallback 开始");
            System.out.println("  message:" + message.getMessageProperties());
            System.out.println("  message:" + message.getBody());
            System.out.println("  replyCode:" + replyCode);
            System.out.println("  replyText:" + replyText);
            System.out.println("  exchange:" + exchange);
            System.out.println("  routingKey:" + routingKey);
            System.out.println("RabbitTemplate.ReturnCallback 结束");
            System.out.println();
        }
    }

}