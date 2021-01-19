package com.lvchao.rabbitmq.consumer.consumer;

import com.lvchao.rabbitmq.constant.RabbitmqConstant;
import com.lvchao.rabbitmq.consumer.entity.LcMessage;
import com.lvchao.rabbitmq.consumer.service.LcRabbitMessageService;
import com.lvchao.rabbitmq.entity.LcRabbitMessage;
import com.lvchao.rabbitmq.utils.JsonUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/16
 */
@Component
public class RabbitmqConsumer {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private LcRabbitMessageService lcRabbitMessageService;
   /* @RabbitHandler
    @RabbitListener(queues = RabbitmqConstant.QUEUE)*/
    /* public void onMessage(LcRabbitMessage message) throws InterruptedException {
        System.out.println(JsonUtils.toString(message));
    }*/
    //配置监听的哪一个队列，同时在没有queue和exchange的情况下会去创建并建立绑定关系
   /* @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue",durable = "true"),
            exchange = @Exchange(name="order-exchange",durable = "true",type = "topic"),
            key = "order.*"
        )
    )*/
  /*  @RabbitListener(queues = RabbitmqConstant.QUEUE)
    @RabbitHandler//如果有消息过来，在消费的时候调用这个方法
    public void consume(Message message, Channel channel) throws IOException{
        //消息体 --body
        String payLoad = message.getPayload().toString();
        //消息头--header
        MessageHeaders mh = message.getHeaders();
        Long tag=(Long) mh.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("消费端收到的消息："+payLoad);
        //手工ack，
        channel.basicAck(tag, false);

    }*/
    @RabbitHandler
    @RabbitListener(queues = RabbitmqConstant.QUEUE)
    @Transactional(rollbackFor = Exception.class) // 如果调用不同的服务需要 调用分布式事务
    public void onLcMessage(@Payload LcRabbitMessage message, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        /*
         * Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，
         * 以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
         * RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
         */
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        /*
         *  multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
         *  如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
         */
        boolean multiple = false;
        LcRabbitMessage lcRabbitMessage = lcRabbitMessageService.getById(message.getId());
        if (lcRabbitMessage == null){
            lcRabbitMessage = LcRabbitMessage.builder().retryNum(0).build();
        }
        LcRabbitMessage build = LcRabbitMessage.builder().id(message.getId()).updateTime(new Date()).serverName(applicationName + ":" + serverPort).consumeFlag(1).retryNum(lcRabbitMessage.getRetryNum() + 1).build();
        try{
            if (StringUtils.isEmpty(message.getMessage())){
                System.out.println("处理的消息为空，结束方法");
                return;
            }
            LcMessage lcMessage = JsonUtils.toBean(message.getMessage(), LcMessage.class);

            // 加锁的目的就是了一起打印
            // synchronized (this){
            // 模拟延迟
            Thread.sleep(new Random().nextInt(1000));
            // 当年龄未偶数时失败 并且 随机数为偶数时 模拟消息处理异常
            if (lcMessage.getAge() / 2 == 0 && new Random().nextInt(10) / 2 == 0){
                throw new RuntimeException("抛出异常");
            }
            // }
            lcRabbitMessageService.updateById(build);
            //ACK,确认一条消息已经被消费
            channel.basicAck(deliveryTag,multiple);
        }catch (Exception e){
            build.setConsumeFlag(2);
            build.setCause(e.getMessage());
            lcRabbitMessageService.updateById(build);
        }
    }
}
