package com.pudge.cn.iot.system.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pudge.cn.iot.system.auth.entity.IotAdminRoleRelation;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 后台用户和角色关系表，用户与角色是多对多关系 Mapper 接口
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Mapper
public interface IotAdminRoleRelationMapper extends BaseMapper<IotAdminRoleRelation> {

}
