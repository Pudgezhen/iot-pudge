package com.pudge.cn.iot.system.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pudge.cn.iot.system.auth.entity.IotAdmin;
import com.pudge.cn.iot.system.auth.entity.IotPermission;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
public interface IIotAdminService extends IService<IotAdmin> {

    IotAdmin getAdminByUsername(String username);

    List<IotPermission> getPermissionList(Long id);
}
