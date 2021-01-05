package com.lvchao.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/5
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     */
    @ExcelProperty("序号")
    private Integer id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private Integer age;
}
