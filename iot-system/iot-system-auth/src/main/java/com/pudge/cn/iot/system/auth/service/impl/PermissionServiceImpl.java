package com.pudge.cn.iot.system.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pudge.cn.iot.api.auth.entity.Permission;
import com.pudge.cn.iot.api.auth.entity.RolePermission;
import com.pudge.cn.iot.system.auth.mapper.PermissionMapper;
import com.pudge.cn.iot.system.auth.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-22
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByMatch(Integer matchMethod) {
        QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("url_match",matchMethod);

        return permissionMapper.selectList(queryWrapper);
    }

    @Override
    public List<RolePermission> allRolePermissions() {
        return permissionMapper.allRolePermissions();
    }
}
