package com.lvchao.easyexcel;

import com.lvchao.easyexcel.entity.Lvchao1;
import com.lvchao.easyexcel.entity.Lvchao2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/6
 */
public class Test03 {
    @Test
    public void test004(){
        Lvchao1 lvchao1 = Lvchao1.builder().age(10).name("吕超").type("未知").build();
        Lvchao2 lvchao2 = new Lvchao2();
        BeanUtils.copyProperties(lvchao1,lvchao2);
        System.out.println(lvchao1);
        System.out.println(lvchao2);
    }

    @Test
    public void test003(){
        StringBuilder errorReason = new StringBuilder("");
        System.out.println(StringUtils.isEmpty(errorReason.toString()));
    }

 //   @Test
    public void test002(){
        StringBuilder errorReason = new StringBuilder("1234567890吕超-");
       /* System.out.println(str.length());
        int i = StringUtils.lastIndexOf(str, "-");
        System.out.println("i:"+i);*/
        if ("-".equals(errorReason.substring(errorReason.length()-1))){
            errorReason = new StringBuilder(errorReason.substring(0,errorReason.length()-1));
        }

        System.out.println(errorReason);
    }

    @Test
    public void test001(){
        List<String> list = new ArrayList<>();//Arrays.asList("吕超","刘备","张三");
        list.add("张三");
        list.add("刘备");
        list.add("里斯");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(0));
            list.remove(0);
        }
    }
}
