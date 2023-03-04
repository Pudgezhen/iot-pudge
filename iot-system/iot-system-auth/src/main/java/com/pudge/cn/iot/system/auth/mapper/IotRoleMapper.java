package com.pudge.cn.iot.system.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pudge.cn.iot.system.auth.entity.IotRole;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Mapper
public interface IotRoleMapper extends BaseMapper<IotRole> {

}
