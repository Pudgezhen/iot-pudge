package com.pudge.cn.iot.system.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pudge.cn.iot.system.auth.entity.IotAdminPermissionRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限 Mapper 接口
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Mapper
public interface IotAdminPermissionRelationMapper extends BaseMapper<IotAdminPermissionRelation> {

}
