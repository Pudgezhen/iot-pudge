package com.pudge.cn.iot.system.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pudge.cn.iot.system.auth.entity.IotPermission;
import com.pudge.cn.iot.system.auth.mapper.IotPermissionMapper;
import com.pudge.cn.iot.system.auth.service.IIotPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Service
public class IotPermissionServiceImpl extends ServiceImpl<IotPermissionMapper, IotPermission> implements IIotPermissionService {

}
