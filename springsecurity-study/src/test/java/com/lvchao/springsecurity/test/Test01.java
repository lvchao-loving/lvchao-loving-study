package com.lvchao.springsecurity.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/20
 */
public class Test01 {

    @Test
    public void test001(){
// 创建密码解析器
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
// 对密码进行加密
        String atguigu = bCryptPasswordEncoder.encode("atguigu");
// 打印加密之后的数据
        System.out.println("加密之后数据：\t"+atguigu);
//判断原字符加密后和加密之前是否匹配
        boolean result = bCryptPasswordEncoder.matches("atguigu", atguigu);
// 打印比较结果
        System.out.println("比较结果：\t"+result);
    }
}
