package com.pudge.cn.iot.system.user.service.impl;

import com.pudge.cn.iot.system.user.entity.Address;
import com.pudge.cn.iot.system.user.mapper.AddressMapper;
import com.pudge.cn.iot.system.user.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
