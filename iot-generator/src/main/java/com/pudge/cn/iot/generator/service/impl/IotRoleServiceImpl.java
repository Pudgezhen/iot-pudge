package com.pudge.cn.iot.generator.service.impl;

import com.pudge.cn.iot.generator.entity.IotRole;
import com.pudge.cn.iot.generator.mapper.IotRoleMapper;
import com.pudge.cn.iot.generator.service.IIotRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
