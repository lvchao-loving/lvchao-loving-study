package com.lvchao.mysql.controller;


import com.lvchao.mysql.aop.OperateLog;
import com.lvchao.mysql.entity.User;
import com.lvchao.mysql.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lvchao
 * @since 2020-12-16
 */
@Api("user对象管理模块")
@RestController
@RequestMapping("/mysql/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询所有user对象")
    @PostMapping(value = "insert")
    @OperateLog
    public void insert( @ApiParam(name = "user",value = "user对象",required = true) @RequestBody User user){
        userService.save(user);
    }

    @ApiOperation(value = "查询user对象")
    @GetMapping("{id}")
    @OperateLog
    public User getUserById(
            @ApiParam(name = "id",value = "user对象的id",required = true)
            @PathVariable Integer id){
        return userService.getUserById(id);
    }

    @ApiOperation(value = "查询user对象List")
    @GetMapping(value = "list")
    @OperateLog
    public List<User> getUserList(){
        return userService.list(null);
    }

    @ApiOperation(value = "更新user对象")
    @PutMapping(value = "updateUserById")
    @OperateLog
    public Integer updateUserById(@ApiParam(name = "user",value = "user对象",required = true) @RequestBody User user){

        return  userService.updateUserById(user);
    }
}

