package com.lvchao.mybatisplus.controller;


import com.lvchao.mybatisplus.entity.LcLog;
import com.lvchao.mybatisplus.service.LcLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lvchao
 * @since 2021-01-04
 */
@RestController
public class LcLogController {
    @Autowired
    private LcLogService lcLogService;

    @GetMapping("/test")
    public String test001(){
        //List<LcLog> list = lcLogService.list(null);
        //list.stream().forEach(System.out::println);

        return "12";
    }
}

