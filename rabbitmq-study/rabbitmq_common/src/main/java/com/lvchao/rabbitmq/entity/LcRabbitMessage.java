package com.lvchao.rabbitmq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lvchao
 * @since 2021-01-16
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LcRabbitMessage对象", description="")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LcRabbitMessage implements Serializable {

    @ApiModelProperty(value = "消息唯一标识 UUID")
    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "json存储的消息")
    private String message;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建者id")
    private String createUserId;

    @ApiModelProperty(value = "创建者名称")
    private String createUserName;

    @ApiModelProperty(value = "发送到交换机正确性（0,未确认，1正确发，2失败）")
    private Integer exchangeFlag;

    @ApiModelProperty(value = "发送到队列正确性（0,未确认，1正确发，2失败）")
    private Integer queueFlag;

    @ApiModelProperty(value = "错误原因")
    private String cause;

    @ApiModelProperty(value = "重试次数，默认0")
    private Integer retryNum;

    @ApiModelProperty(value = "是否消费（0 未消费 1 已消费）")
    private Integer consumeFlag;

    @ApiModelProperty(value = "消费者服务名称")
    private String serverName;
}
