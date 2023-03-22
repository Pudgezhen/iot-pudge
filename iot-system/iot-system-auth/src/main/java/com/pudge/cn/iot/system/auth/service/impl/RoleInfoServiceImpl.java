package com.pudge.cn.iot.system.auth.service.impl;

import com.pudge.cn.iot.api.auth.entity.RoleInfo;
import com.pudge.cn.iot.system.auth.mapper.RoleInfoMapper;
import com.pudge.cn.iot.system.auth.service.IRoleInfoService;
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
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IRoleInfoService {

}
