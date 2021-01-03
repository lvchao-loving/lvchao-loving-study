package com.lvchao.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/19
 */
@Controller
public class TestController {
    @GetMapping("")
    @ResponseBody
    public String index(){
        return "index......";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(){
        return "logout......";
    }

    @GetMapping("/test/hello")
    @ResponseBody
    public String testHello(){
        return "testHello......";
    }

    @GetMapping("/test/index")
    @ResponseBody
    public String testIndex(){
        return "testIndex......";
    }

    @GetMapping("/user/login")
    @ResponseBody
    public String userLogin(){
        return "userLogin......";
    }
}
