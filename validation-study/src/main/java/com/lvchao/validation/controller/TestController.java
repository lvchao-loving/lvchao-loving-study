package com.lvchao.validation.controller;

import com.lvchao.validation.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/25
 */
@RestController
//@Validated // 注意 如果校验非引用对象 （例如：test1和test2）需要再类上添加此注解 ，否则不需要添加
public class TestController {
    @GetMapping("/test1")
    public String test1(@NotNull String id){
        return "测试结果:" + id;
    }
    @GetMapping("/test2")
    public String test2(@NotNull @Min(1) @Max(20) Integer id){
        return "测试结果:" + id;
    }

    // 如果校验引用对象 需要在方法上添加@Valid或@Validated
    @GetMapping("/test3")
    public String test3(@Valid User user){
        return "测试结果:" + user;
    }

    // 如果校验引用对象 需要在方法上添加@Valid或@Validated
    @GetMapping("/test4")
    public String test4(@Validated User user){
        return "测试结果:" + user;
    }

}