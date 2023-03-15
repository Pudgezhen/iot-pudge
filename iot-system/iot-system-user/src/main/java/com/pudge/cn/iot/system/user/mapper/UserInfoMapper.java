package com.pudge.cn.iot.system.user.mapper;

import com.pudge.cn.iot.api.user.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
