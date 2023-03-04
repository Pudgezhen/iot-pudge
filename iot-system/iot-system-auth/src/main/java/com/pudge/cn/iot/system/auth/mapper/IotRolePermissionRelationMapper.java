package com.pudge.cn.iot.system.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pudge.cn.iot.system.auth.entity.IotRolePermissionRelation;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 后台用户角色和权限关系表，角色与权限是多对多关系 Mapper 接口
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Mapper
public interface IotRolePermissionRelationMapper extends BaseMapper<IotRolePermissionRelation> {

}
