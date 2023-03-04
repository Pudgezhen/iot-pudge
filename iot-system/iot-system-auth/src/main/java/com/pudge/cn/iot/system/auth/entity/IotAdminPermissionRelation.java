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
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("iot_admin_permission_relation")
public class IotAdminPermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("admin_id")
    private Long adminId;

    @TableField("permission_id")
    private Long permissionId;


}
