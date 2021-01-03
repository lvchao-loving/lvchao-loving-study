package com.lvchao.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/22
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "success....";
    }
}
