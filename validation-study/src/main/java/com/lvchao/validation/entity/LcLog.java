package com.lvchao.validation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lvchao
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LcLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String url;

    private String method;

    private String ip;

    private String params;

    private String result;

    private Date createTime;


}
