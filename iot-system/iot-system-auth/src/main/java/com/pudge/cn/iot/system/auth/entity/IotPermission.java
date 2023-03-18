package com.pudge.cn.iot.system.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台用户权限表
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iot_permission")
public class IotPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "globalIdGenerate", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 父id
     */
    @TableField("pid")
    private Long pid;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    @TableField("value")
    private String value;

    @TableField("icon")
    private String icon;

    /**
     * 类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 路径
     */
    @TableField("uri")
    private String uri;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


}
