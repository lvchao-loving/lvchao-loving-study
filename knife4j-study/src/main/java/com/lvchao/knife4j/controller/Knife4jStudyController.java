package com.lvchao.knife4j.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/9
 */
@Api(tags = "首页模块")
@RestController
public class Knife4jStudyController {
    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "上传name")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }


    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "文件流的名称", required = true,paramType="body",dataType = "file"),
        @ApiImplicitParam(paramType = "body", name = "name",value = "姓名",required = true,dataType = "string")
    })
    @ApiOperation(value = "上传name、file")
  //  @ApiResponses(@ApiResponse()) 选择性配置
    @PostMapping("/sayHi1")
    public ResponseEntity<String> sayHi1(@RequestParam(value = "name")String name, MultipartFile file){
        return ResponseEntity.ok("Hi:"+name);
    }
}
