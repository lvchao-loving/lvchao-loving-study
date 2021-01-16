package com.lvchao.rabbitmq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lvchao
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@ApiModel(value="LcRabbitMessage对象", description="")
@TableName("lc_rabbit_message")
public class LcRabbitMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id")
    private String id;

    private String message;

    private Date createTime;

    private Date updateTime;

    private String createUserId;

    private String createUserName;

    private Integer exchangeFlag;

    private Integer queueFlag;

    /**
     * 错误原因
     */
    private String cause;
}
