package com.pudge.cn.iot.system.auth.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限 前端控制器
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@RestController
@RequestMapping("/iotAdminPermissionRelation")
public class IotAdminPermissionRelationController {

}
