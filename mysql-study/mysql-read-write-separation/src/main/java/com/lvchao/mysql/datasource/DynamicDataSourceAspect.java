package com.lvchao.mysql.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)  // 该切面应当先于 @Transactional 执行
@Component
@Slf4j
public class DynamicDataSourceAspect {

    /**
     * 切换数据源
     *
     * @param point
     * @param dataSource
     */
    @Before("execution(* com.lvchao.mysql.service.*.*(..)) && @annotation(dataSource))")
    public void switchDataSource(JoinPoint point, DataSource dataSource) {
        if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value().name())) {
            log.debug("DataSource [{}] 不存在，使用默认 DataSource [{}] ",
                    dataSource.value(),
                    DynamicDataSourceContextHolder.getDataSourceKey());
        } else {
            // 切换数据源
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value().name());
            log.debug("切换 DataSource 至 [{}] ，引起切换方法是 [{}]",DynamicDataSourceContextHolder.getDataSourceKey(),point.getSignature());
        }
    }

    /**
     * 重置数据源
     *
     * @param point
     * @param dataSource
     */
    @After("execution(* com.lvchao.mysql.service.*.*(..)) && @annotation(dataSource))")
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.debug("重置 DataSource 至 [{}] ，引起重置的方法是 [{}]", DynamicDataSourceContextHolder.getDataSourceKey(),point.getSignature());
    }
}