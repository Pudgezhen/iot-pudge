package com.pudge.cn.iot.system.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pudge.cn.iot.system.auth.entity.IotAdmin;
import com.pudge.cn.iot.system.auth.entity.IotPermission;
import com.pudge.cn.iot.system.auth.mapper.IotAdminMapper;
import com.pudge.cn.iot.system.auth.service.IIotAdminService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-04
 */
@Service
public class IotAdminServiceImpl extends ServiceImpl<IotAdminMapper, IotAdmin> implements IIotAdminService {

    @Autowired
    private IotAdminMapper iotAdminMapper;

    @Override
    public IotAdmin getAdminByUsername(String username) {
        QueryWrapper<IotAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(username);
        return iotAdminMapper.selectOne(queryWrapper);
    }

    @Override
    public List<IotPermission> getPermissionList(Long id) {
        return null;
    }
}
