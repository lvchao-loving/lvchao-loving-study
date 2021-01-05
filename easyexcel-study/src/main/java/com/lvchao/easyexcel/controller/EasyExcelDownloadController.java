package com.lvchao.easyexcel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/5
 */

@RestController
@Slf4j
public class EasyExcelDownloadController {
    // 获取当前系统的临时目录
    private static final String FilePath = System.getProperty("java.io.tmpdir") + File.separator;

  /*  public static void main(String[] args) {
        System.out.println(FilePath);
    }*/
}
