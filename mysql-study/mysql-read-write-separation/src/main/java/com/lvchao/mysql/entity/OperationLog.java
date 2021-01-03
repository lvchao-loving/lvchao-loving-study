package com.lvchao.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lvchao
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OperationLog对象", description="")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "操作类")
    private String operateClass;

    @ApiModelProperty(value = "操作方法")
    private String operateMethod;

    @ApiModelProperty(value = "返回值类型")
    private String returnClass;

    @ApiModelProperty(value = "操作用户")
    private String operateUser;

    @ApiModelProperty(value = "操作时间")
    private String operateTime;

    @ApiModelProperty(value = "请求参数名及参数值")
    private String paramAndValue;

    @ApiModelProperty(value = "执行方法耗时, 单位 ms")
    private Long costTime;

    @ApiModelProperty(value = "返回值")
    private String returnValue;


}
