package com.lvchao.shardingjdbc.entity;

import lombok.Data;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/17
 */
@Data
public class Course {
    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;
}
