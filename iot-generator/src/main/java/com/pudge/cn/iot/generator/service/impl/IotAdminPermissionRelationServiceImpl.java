package com.pudge.cn.iot.generator.service.impl;

import com.pudge.cn.iot.generator.entity.IotAdminPermissionRelation;
import com.pudge.cn.iot.generator.mapper.IotAdminPermissionRelationMapper;
import com.pudge.cn.iot.generator.service.IIotAdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
