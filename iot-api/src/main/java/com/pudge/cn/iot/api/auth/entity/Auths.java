package com.pudge.cn.iot.api.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author mu_zhen
 * @description Excel 操作工具类
 * @Date 2023/3/22 21:33
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auths implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色信息
     */
    private RoleInfo roleInfo;

    /**
     * 权限信息
     */
    private List<Permission> permissions;

}
