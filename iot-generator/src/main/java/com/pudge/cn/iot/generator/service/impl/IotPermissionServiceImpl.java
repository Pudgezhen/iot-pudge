package com.pudge.cn.iot.generator.service.impl;

import com.pudge.cn.iot.generator.entity.IotPermission;
import com.pudge.cn.iot.generator.mapper.IotPermissionMapper;
import com.pudge.cn.iot.generator.service.IIotPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
