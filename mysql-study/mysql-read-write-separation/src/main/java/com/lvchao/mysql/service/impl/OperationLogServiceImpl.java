package com.lvchao.mysql.service.impl;

import com.lvchao.mysql.entity.OperationLog;
import com.lvchao.mysql.mapper.OperationLogMapper;
import com.lvchao.mysql.service.OperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvchao
 * @since 2020-12-16
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

}
