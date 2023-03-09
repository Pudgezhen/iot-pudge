package com.pudge.cn.iot.generator.service.impl;

import com.pudge.cn.iot.generator.entity.IotRolePermissionRelation;
import com.pudge.cn.iot.generator.mapper.IotRolePermissionRelationMapper;
import com.pudge.cn.iot.generator.service.IIotRolePermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
