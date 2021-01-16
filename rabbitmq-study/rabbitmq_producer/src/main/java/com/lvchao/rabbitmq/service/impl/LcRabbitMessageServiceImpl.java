package com.lvchao.rabbitmq.service.impl;

import com.lvchao.rabbitmq.entity.LcRabbitMessage;
import com.lvchao.rabbitmq.mapper.LcRabbitMessageMapper;
import com.lvchao.rabbitmq.service.LcRabbitMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvchao
 * @since 2021-01-14
 */
@Service
public class LcRabbitMessageServiceImpl extends ServiceImpl<LcRabbitMessageMapper, LcRabbitMessage> implements LcRabbitMessageService {

}
