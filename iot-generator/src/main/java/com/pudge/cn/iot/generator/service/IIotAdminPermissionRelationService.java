package com.pudge.cn.iot.generator.service;

import com.pudge.cn.iot.generator.entity.IotAdminPermissionRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限 服务类
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
public interface IIotAdminPermissionRelationService extends IService<IotAdminPermissionRelation> {

}
