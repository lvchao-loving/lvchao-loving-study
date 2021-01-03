package com.lvchao.validation.test;

import com.lvchao.validation.entity.User;
import org.junit.Test;


/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/24
 */
public class Test01 {
    @Test
    public void test001(){
        User user = new User();
        user.setId(1L);
        user.setName("吕超");
        user.setUsername("测试");
        user.setAge(900);
//        List<String> valid = ValidationUtil.valid(user);
//        System.out.println(valid);
    }

    @Test
    public void test002(){
        User build = User.builder().build();
      //  User.UserBuilder builder = (User.UserBuilder) build;
        System.out.println(build);
    }
}
