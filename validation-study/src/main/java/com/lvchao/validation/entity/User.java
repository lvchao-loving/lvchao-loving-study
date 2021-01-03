package com.lvchao.validation.entity;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;

    @NotNull
    private String name;
   /* @NotBlank(message = "{required}")*/
    @NotBlank
    private String username;
    @Min(0)
    @Max(200)
    private Integer age;
    private String email;
}
