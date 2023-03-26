package com.pudge.cn.iot.system.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pudge.cn.iot.api.auth.entity.Permission;
import com.pudge.cn.iot.api.auth.entity.RolePermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pudge
 * @since 2023-03-22
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT * FROM role_permission")
    List<RolePermission> allRolePermissions();
}
