package com.lvchao.redis.annotation;

import com.lvchao.redis.config.LvchaoLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LvchaoLettuceRedisConfigure.class)
public @interface EnableLvchaoLettuceRedis {
}
