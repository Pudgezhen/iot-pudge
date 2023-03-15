package com.pudge.cn.iot.system.user.service.impl;

import com.pudge.cn.iot.system.user.entity.UserInfo;
import com.pudge.cn.iot.system.user.mapper.UserInfoMapper;
import com.pudge.cn.iot.system.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
