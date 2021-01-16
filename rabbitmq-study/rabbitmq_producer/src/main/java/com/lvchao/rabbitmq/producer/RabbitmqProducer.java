package com.lvchao.rabbitmq.producer;

import com.lvchao.rabbitmq.entity.LcRabbitMessage;
import com.lvchao.rabbitmq.message.LcMessage;
import com.lvchao.rabbitmq.service.LcRabbitMessageService;
import com.lvchao.rabbitmq.utils.HanZiUtil;
import com.lvchao.rabbitmq.utils.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Component
public class RabbitmqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private LcRabbitMessageService lcRabbitMessageService;

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

    /**
     * 发送消息并存储到数据库中
     *
     * @param num 发送消息的数量
     * @param exchangeName 交换机的名称
     * @param routingKey 交换机绑定的 routingkey
     */
    @Transactional(rollbackFor = Exception.class)
    public void syncSend(Integer num,String exchangeName,String routingKey) {
        LcRabbitMessage lcRabbitMessage = loadlcRabbitMessage();
        // 创建消息的唯一标识
        CorrelationData correlationData = new CorrelationData();
        /**
         * 这个Id保证消息全局唯一，在可靠性投递中,就用该Id来找到未成功投递的消息
         */
        correlationData.setId(lcRabbitMessage.getId());
        lcRabbitMessageService.save(lcRabbitMessage);
        // 同步发送消息
        rabbitTemplate.convertAndSend(exchangeName,routingKey,lcRabbitMessage,correlationData);
    }

    /**
     * 构建并返回数据库从出对象
     * @return
     */
    private LcRabbitMessage loadlcRabbitMessage(){
        return LcRabbitMessage.builder().id(UUID.randomUUID().toString().replace("-","")).message(JsonUtils.toString(loadMessage())).createTime(new Date())
                .updateTime(new Date()).exchangeFlag(0).queueFlag(0).createUserId("lvchao").createUserName("吕超").build();
    }

    /**
     * 创建消息对象并构建数据返回
     * @return
     */
    private LcMessage loadMessage(){
        return LcMessage.builder().id(UUID.randomUUID().toString().replace("-",""))
                .age(new Random().nextInt(100)).name(HanZiUtil.getRandomHanZiNoSpace(3))
                .address(HanZiUtil.getRandomHanZiNoSpace(15)).createDate(sDateFormat.format(new Date())).build();
    }

}

