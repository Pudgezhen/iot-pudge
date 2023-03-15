package com.pudge.cn.iot.system.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pudge.cn.iot.api.user.entity.Areas;
import com.pudge.cn.iot.system.user.mapper.AreasMapper;
import com.pudge.cn.iot.system.user.service.IAreasService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区域县区信息表 服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Service
public class AreasServiceImpl extends ServiceImpl<AreasMapper, Areas> implements IAreasService {

}
