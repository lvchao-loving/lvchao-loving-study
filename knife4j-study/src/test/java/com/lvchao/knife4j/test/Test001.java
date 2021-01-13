package com.lvchao.knife4j.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/12
 */
public class Test001 {
    @Test
    public void test0001(){
     //   String clientDetail = Optional.ofNullable("123").orElseThrow(0);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(2,333);
        Integer clientDetail = Optional.ofNullable(map.get(3)).orElse(0);
        System.out.println(clientDetail);
    }
}
