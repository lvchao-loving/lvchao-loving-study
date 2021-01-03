package com.lvchao.redis.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/23
 */
@Data
public class User {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotNull
    private Integer age;

}
