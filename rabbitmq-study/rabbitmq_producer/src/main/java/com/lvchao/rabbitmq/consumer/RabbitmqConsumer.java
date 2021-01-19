package com.lvchao.rabbitmq.consumer;

import com.lvchao.rabbitmq.constant.RabbitmqConstant;
import com.lvchao.rabbitmq.entity.LcRabbitMessage;
import com.lvchao.rabbitmq.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq 消费者
 */
// @Component
public class RabbitmqConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    @RabbitListener(queues = RabbitmqConstant.QUEUE)
    public void onMessage(LcRabbitMessage message) throws InterruptedException {
        System.out.println(JsonUtils.toString(message));
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}