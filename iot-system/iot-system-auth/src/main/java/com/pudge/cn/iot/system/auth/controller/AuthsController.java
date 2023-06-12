package com.pudge.cn.iot.system.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pudge.cn.iot.api.auth.entity.Auths;
import com.pudge.cn.iot.api.auth.entity.Permission;
import com.pudge.cn.iot.api.auth.entity.RoleInfo;
import com.pudge.cn.iot.api.auth.entity.RolePermission;
import com.pudge.cn.iot.system.auth.service.IPermissionService;
import com.pudge.cn.iot.system.auth.service.IRoleInfoService;
import com.pudge.cn.iot.system.auth.service.IRolePermissionService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/3/22 21:40
 */

@RestController
@RequestMapping("/auth")
public class AuthsController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Autowired
    private IRoleInfoService roleInfoService;


    @GetMapping("/getAuths")
    public Auths getAuths(@RequestParam("roleId") int roleId){
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",roleId);
        List<RolePermission> rolePermissionList = rolePermissionService.list(queryWrapper);

        RoleInfo roleInfo = roleInfoService.getById(roleId);

        List<Permission> permissions = new ArrayList<>();

        for (RolePermission rolePermission : rolePermissionList) {
            Permission permission = permissionService.getById(rolePermission.getPid());
            permissions.add(permission);
        }
        return new Auths(roleInfo,permissions);
    }




}
