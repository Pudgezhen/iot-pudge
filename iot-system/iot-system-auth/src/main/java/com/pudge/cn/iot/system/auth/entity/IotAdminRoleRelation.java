package com.pudge.cn.iot.system.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 后台用户和角色关系表，用户与角色是多对多关系
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iot_admin_role_relation")
public class IotAdminRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "globalIdGenerate", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField("admin_id")
    private Long adminId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;


}
