package com.lvchao.rabbitmq.config;

import com.lvchao.rabbitmq.constant.RabbitmqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        // 创建 Queue
        @Bean
        public Queue lcQueue() {
            return new Queue(RabbitmqConstant.QUEUE, // Queue 名字
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

        // 创建 Binding
        // Exchange：RabbitmqConstant.EXCHANGE
        // Routing key：RabbitmqConstant.ROUTING_KEY
        // Queue：RabbitmqConstant.QUEUE
        @Bean
        public Binding lcBinding() {
            return BindingBuilder.bind(lcQueue()).to(lcExchange()).with(RabbitmqConstant.ROUTING_KEY);
        }

    }

}
