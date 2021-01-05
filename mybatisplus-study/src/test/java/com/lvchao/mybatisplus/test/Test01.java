package com.lvchao.mybatisplus.test;

import com.lvchao.mybatisplus.entity.LcLog;
import com.lvchao.mybatisplus.service.LcLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/4
 */
@SpringBootTest
public class Test01 {
    @Autowired
    private LcLogService lcLogService;

    @Test
    public void test001(){
        List<LcLog> list = lcLogService.list(null);
        list.stream().forEach(System.out::println);
    }
}
