package com.pudge.cn.iot.system.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pudge.cn.iot.system.auth.entity.IotAdminPermissionRelation;
import com.pudge.cn.iot.system.auth.mapper.IotAdminPermissionRelationMapper;
import com.pudge.cn.iot.system.auth.service.IIotAdminPermissionRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Service
public class IotAdminPermissionRelationServiceImpl extends ServiceImpl<IotAdminPermissionRelationMapper, IotAdminPermissionRelation> implements IIotAdminPermissionRelationService {

}
