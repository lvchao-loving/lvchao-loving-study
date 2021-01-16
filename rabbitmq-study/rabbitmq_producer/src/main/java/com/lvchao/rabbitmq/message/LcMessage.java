package com.lvchao.rabbitmq.message;

import lombok.*;

import java.util.Date;

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
public class LcMessage {
    /**
     * 消息id
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
