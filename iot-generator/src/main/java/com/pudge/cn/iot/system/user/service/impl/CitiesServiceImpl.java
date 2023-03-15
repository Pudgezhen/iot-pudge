package com.pudge.cn.iot.system.user.service.impl;

import com.pudge.cn.iot.system.user.entity.Cities;
import com.pudge.cn.iot.system.user.mapper.CitiesMapper;
import com.pudge.cn.iot.system.user.service.ICitiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区域地州市信息表 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Service
public class CitiesServiceImpl extends ServiceImpl<CitiesMapper, Cities> implements ICitiesService {

}
