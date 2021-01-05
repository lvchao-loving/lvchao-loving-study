package com.lvchao.validation.test;

import com.lvchao.validation.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        List<User> list = new ArrayList<>();
        list.add(User.builder().id(1L).age(10).name("张1").build());
        list.add(User.builder().id(2L).age(20).name("张2").build());
        list.add(User.builder().id(3L).age(30).name("张3").build());
        list.stream().filter(e -> {
            e.setId(4L);
            return true;
        }).collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }
}
