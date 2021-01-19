package com.lvchao.rabbitmq.consumer.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/15
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LcMessage implements Serializable {
    /**
     * 数据id
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 居住
     */
    private String address;

    /**
     * 创建时间
     */
    private String createDate;
}
