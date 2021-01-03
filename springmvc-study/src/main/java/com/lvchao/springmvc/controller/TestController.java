package com.lvchao.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/19
 */
@RestController
public class TestController {
    @GetMapping("/lvchao/hello")
    public String getTest(){
        String resut = "/lvchao/hello---" + UUID.randomUUID().toString().replace("-","");
        System.out.println(resut);
        return resut;
    }

    @GetMapping("/test/hello")
    public String getTest1(){
        String resut = "/test/hello---" + UUID.randomUUID().toString().replace("-","");
        System.out.println(resut);
        return resut;
    }
}
