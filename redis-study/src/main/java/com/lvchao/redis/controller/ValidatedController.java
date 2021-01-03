package com.lvchao.redis.controller;

import com.lvchao.redis.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/22
 */
//@Validated
@RestController
public class ValidatedController {

    @GetMapping("/validated/{id}") // @NotBlank(message = "{required}")
    public String validated1(@PathVariable String id){
        System.out.println(UUID.randomUUID().toString().replace("-",""));
        return id;
    }

    @GetMapping("/validated") // )
    public String validated2(@NotNull String id){
        System.out.println(UUID.randomUUID().toString().replace("-",""));
        return id;
    }
    @GetMapping("/validated/user") // )
    public String validated3(@Validated User user){
        System.out.println(UUID.randomUUID().toString().replace("-",""));
        return user.toString();
    }
}
