package com.lvchao.rabbitmq.consumer.service.impl;

import com.lvchao.rabbitmq.consumer.mapper.LcRabbitMessageMapper;
import com.lvchao.rabbitmq.consumer.service.LcRabbitMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvchao.rabbitmq.entity.LcRabbitMessage;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvchao
 * @since 2021-01-16
 */
@Service
public class LcRabbitMessageServiceImpl extends ServiceImpl<LcRabbitMessageMapper, LcRabbitMessage> implements LcRabbitMessageService {

}
