package com.pudge.cn.iot.system.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pudge.cn.iot.system.auth.entity.IotRolePermissionRelation;
import com.pudge.cn.iot.system.auth.mapper.IotRolePermissionRelationMapper;
import com.pudge.cn.iot.system.auth.service.IIotRolePermissionRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色和权限关系表，角色与权限是多对多关系 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Service
public class IotRolePermissionRelationServiceImpl extends ServiceImpl<IotRolePermissionRelationMapper, IotRolePermissionRelation> implements IIotRolePermissionRelationService {

}
