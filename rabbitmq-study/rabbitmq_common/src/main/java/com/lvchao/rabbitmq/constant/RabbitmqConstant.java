package com.lvchao.rabbitmq.constant;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/15
 */
public class RabbitmqConstant {

    /**
     * 普通队列
     */
    public static final String QUEUE = "lc_queue";

    /**
     * 普通队列的交换机
     */
    public static final String EXCHANGE = "lc_exchange";

    /**
     * 死信队列的交换机
     */
    public static final String DLXEXCANGE = "lc_dlx_exchange";

    /**
     * 死信队列
     */
    public static final String DLXQUEUE = "lc_dlx_queue";

    /**
     * 普通队列绑定交换机的路由键
     */
    public static final String ROUTING_KEY = "lc_rounting_key";
}
