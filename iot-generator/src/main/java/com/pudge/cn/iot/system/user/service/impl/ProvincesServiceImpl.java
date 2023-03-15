package com.pudge.cn.iot.system.user.service.impl;

import com.pudge.cn.iot.system.user.entity.Provinces;
import com.pudge.cn.iot.system.user.mapper.ProvincesMapper;
import com.pudge.cn.iot.system.user.service.IProvincesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 省份信息表 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Service
public class ProvincesServiceImpl extends ServiceImpl<ProvincesMapper, Provinces> implements IProvincesService {

}
