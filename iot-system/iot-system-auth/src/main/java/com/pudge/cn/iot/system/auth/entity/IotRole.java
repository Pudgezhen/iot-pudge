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
 * 后台用户角色表
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iot_role")
public class IotRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    @TableField("admin_count")
    private Integer adminCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 状态
0：使用
1：失效
     */
    @TableField("status")
    private Integer status;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


}
