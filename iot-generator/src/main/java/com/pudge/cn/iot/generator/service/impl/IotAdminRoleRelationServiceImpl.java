package com.pudge.cn.iot.generator.service.impl;

import com.pudge.cn.iot.generator.entity.IotAdminRoleRelation;
import com.pudge.cn.iot.generator.mapper.IotAdminRoleRelationMapper;
import com.pudge.cn.iot.generator.service.IIotAdminRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和角色关系表，用户与角色是多对多关系 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Service
public class IotAdminRoleRelationServiceImpl extends ServiceImpl<IotAdminRoleRelationMapper, IotAdminRoleRelation> implements IIotAdminRoleRelationService {

}
