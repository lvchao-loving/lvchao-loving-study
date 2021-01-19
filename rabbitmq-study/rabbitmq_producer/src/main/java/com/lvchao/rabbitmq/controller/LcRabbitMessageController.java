package com.lvchao.rabbitmq.controller;


import com.lvchao.rabbitmq.entity.LcMessage;
import com.lvchao.rabbitmq.entity.LcRabbitMessage;
import com.lvchao.rabbitmq.producer.RabbitmqProducer;
import com.lvchao.rabbitmq.service.LcRabbitMessageService;
import com.lvchao.rabbitmq.utils.HanZiUtil;
import com.lvchao.rabbitmq.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lvchao
 * @since 2021-01-14
 */
@RestController
@RequestMapping("/rabbitmq/lc-rabbit-message")
public class LcRabbitMessageController {
    @Autowired
    private LcRabbitMessageService lcRabbitMessageService;

    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

    @GetMapping("syncSend")
    public String syncSend(Integer num,String exchangeName,String routingKey) throws InterruptedException {
        for (int i = 1; i <= num; i++) {
            Thread.sleep(new Random().nextInt(300));
            rabbitmqProducer.syncSend(i,exchangeName,routingKey);
        }
        return "syncSend success......";
    }

    @PostMapping("add")
    public String add(@RequestBody LcRabbitMessage lcRabbitMessage11){
        LcRabbitMessage lcRabbitMessage = new LcRabbitMessage();
        lcRabbitMessage.setId(UUID.randomUUID().toString().replace("-",""));
       // lcRabbitMessage.setMessage(JsonUtils.toString(loadLcMessage()));
        lcRabbitMessage.setMessage("发送的消息...");
        lcRabbitMessage.setCreateTime(new Date());
        lcRabbitMessage.setUpdateTime(new Date());
        lcRabbitMessage.setQueueFlag(0);
        lcRabbitMessage.setExchangeFlag(0);
        lcRabbitMessage.setConsumeFlag(0);
        lcRabbitMessage.setCreateUserId("lvchao");
        lcRabbitMessage.setCreateUserName("吕超");
        System.out.println("添加前：" + lcRabbitMessage.toString());
        lcRabbitMessageService.save(lcRabbitMessage);
        System.out.println("添加后：" + lcRabbitMessage.toString());
        return "add success ... ";
    }
    /**
     * 构建并返回数据库从出对象
     * @return
     */
    private LcRabbitMessage loadlcRabbitMessage(){
        LcRabbitMessage lcRabbitMessage = new LcRabbitMessage();
        lcRabbitMessage.setId(UUID.randomUUID().toString().replace("-",""));
        lcRabbitMessage.setMessage(JsonUtils.toString(loadLcMessage()));
        lcRabbitMessage.setCreateTime(new Date());
        lcRabbitMessage.setUpdateTime(new Date());
        lcRabbitMessage.setQueueFlag(0);
        lcRabbitMessage.setExchangeFlag(0);
        lcRabbitMessage.setConsumeFlag(0);
        lcRabbitMessage.setCreateUserId("lvchao");
        lcRabbitMessage.setCreateUserName("吕超");
        return lcRabbitMessage;
    }


    /**
     * 创建消息对象并构建数据返回
     * @return
     */
    private LcMessage loadLcMessage(){
        LcMessage lcMessage = new LcMessage();
       // lcMessage.setId(UUID.randomUUID().toString().replace("-",""));
        lcMessage.setId("lcmessage id.");
        lcMessage.setAge(new Random().nextInt(100));
        lcMessage.setName(HanZiUtil.getRandomHanZiNoSpace(3));
        lcMessage.setAddress(HanZiUtil.getRandomHanZiNoSpace(15));
        lcMessage.setCreateDate(sDateFormat.format(new Date()));
        return lcMessage;
    }

}

