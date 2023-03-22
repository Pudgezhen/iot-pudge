package com.pudge.cn.iot.system.auth.service.impl;


import com.pudge.cn.iot.api.auth.entity.Permission;
import com.pudge.cn.iot.system.auth.mapper.PermissionMapper;
import com.pudge.cn.iot.system.auth.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-22
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
