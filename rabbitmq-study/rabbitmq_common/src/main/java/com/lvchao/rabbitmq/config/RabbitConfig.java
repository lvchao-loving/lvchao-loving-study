package com.lvchao.rabbitmq.config;

import com.lvchao.rabbitmq.constant.RabbitmqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/14
 */
@Configuration
public class RabbitConfig {

    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建 Queue， 不绑定死信队列使用
       /* @Bean
        public Queue lcQueue() {
            return new Queue(RabbitmqConstant.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }*/

        @Bean
        public Queue lcDlxQueue() {
            return new Queue(RabbitmqConstant.DLXQUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange lcExchange() {
            return new DirectExchange(RabbitmqConstant.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        @Bean
        public DirectExchange dlxExchange() {
            return new DirectExchange(RabbitmqConstant.DLXEXCANGE,true,false);
        }

        // 创建 Binding
        // Exchange：RabbitmqConstant.EXCHANGE
        // Routing key：RabbitmqConstant.ROUTING_KEY
        // Queue：RabbitmqConstant.QUEUE
        @Bean
        public Binding lcBinding() {
            return BindingBuilder.bind(lcQueue()).to(lcExchange()).with(RabbitmqConstant.ROUTING_KEY);
        }

        @Bean
        public Binding lcDlxBinding() {
            return BindingBuilder.bind(lcDlxQueue()).to(dlxExchange()).with(RabbitmqConstant.ROUTING_KEY);
        }

        /**
         * 为队列绑定死信队列
         * @return
         */
        @Bean
        public Queue lcQueue() {
            Map<String, Object> args = new HashMap<>(3);
            // 声明死信交换器
            args.put("x-dead-letter-exchange", RabbitmqConstant.DLXEXCANGE);
            // 声明死信路由键
            args.put("x-dead-letter-routing-key", RabbitmqConstant.ROUTING_KEY);
            // 声明队列消息过期时间 10 秒钟
            args.put("x-message-ttl", 10000);
            //  args.put("x-message-ttl", 10000);
            return new Queue(RabbitmqConstant.QUEUE, true, false, false, args);//队列持久
        }

    }

}
