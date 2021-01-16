package com.lvchao.rabbitmq.core;

import com.lvchao.rabbitmq.entity.LcRabbitMessage;
import com.lvchao.rabbitmq.service.LcRabbitMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RabbitProducerConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private LcRabbitMessageService lcRabbitMessageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

   // private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 消息的唯一标识
        String id = correlationData.getId();
        LcRabbitMessage build = LcRabbitMessage.builder().id(id).cause(cause).updateTime(new Date()).build();
        if (ack) {
            build.setExchangeFlag(1);
            lcRabbitMessageService.updateById(build);
            // 发送到交换机成功（这里被触发只能说明确保发送了到了交换机，但是并不保证确保发送到队列）
            //logger.info("[confirm][Confirm 成功 correlationData: {}]", correlationData);
        } else {
            build.setExchangeFlag(2);
            lcRabbitMessageService.updateById(build);
            // 发送到交换机失败
            //logger.error("[confirm][Confirm 失败 correlationData: {} cause: {}]", correlationData, cause);
        }
    }

}