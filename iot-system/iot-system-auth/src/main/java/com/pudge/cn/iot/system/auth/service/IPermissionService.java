package com.pudge.cn.iot.system.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pudge.cn.iot.api.auth.entity.Permission;
import com.pudge.cn.iot.api.auth.entity.RolePermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pudge
 * @since 2023-03-22
 */
public interface IPermissionService extends IService<Permission> {

    /**
     *  根据不同的匹配方式查询权限列表
     * @param matchMethod 匹配方式
     * @return 权限列表
     */
    List<Permission> findByMatch(Integer matchMethod);

    /**
     * 查询所有的角色权限对应关系
     * @return 角色权限对应关系
     */
    List<RolePermission> allRolePermissions();

}
