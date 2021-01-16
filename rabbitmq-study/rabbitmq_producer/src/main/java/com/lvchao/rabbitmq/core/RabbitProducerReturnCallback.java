package com.lvchao.rabbitmq.core;

import com.lvchao.rabbitmq.entity.LcRabbitMessage;
import com.lvchao.rabbitmq.service.LcRabbitMessageService;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * 说明：此回调方法只有在未发送到队列中才会被调用，成功发送则不会被调用
 * @author lvchao
 */
@Component
public class RabbitProducerReturnCallback implements RabbitTemplate.ReturnsCallback {

    @Autowired
    private LcRabbitMessageService lcRabbitMessageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * ReturnedMessage 对象中包含了很多属性，按需获取
     * @param returnedMessage
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        // 获取发送消息的唯一标识
        String correlationId = (String)returnedMessage.getMessage().getMessageProperties().getHeaders().get("spring_returned_message_correlation");
        LcRabbitMessage build = LcRabbitMessage.builder().id(correlationId).cause("未成功发送到队列中(exchange:" + returnedMessage.getExchange() + ",routingKey" + returnedMessage.getRoutingKey() + ")").updateTime(new Date()).queueFlag(2).build();
        lcRabbitMessageService.updateById(build);
    }

}