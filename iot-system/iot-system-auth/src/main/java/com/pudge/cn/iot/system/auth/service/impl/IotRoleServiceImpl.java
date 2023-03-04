package com.pudge.cn.iot.system.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pudge.cn.iot.system.auth.entity.IotRole;
import com.pudge.cn.iot.system.auth.mapper.IotRoleMapper;
import com.pudge.cn.iot.system.auth.service.IIotRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Service
public class IotRoleServiceImpl extends ServiceImpl<IotRoleMapper, IotRole> implements IIotRoleService {

}
