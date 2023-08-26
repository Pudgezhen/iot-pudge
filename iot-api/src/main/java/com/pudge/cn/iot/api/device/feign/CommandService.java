package com.pudge.cn.iot.api.device.feign;


import com.pudge.cn.iot.api.device.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/8/25 23:04
 */
@Component
@FeignClient("iot-device")
public interface CommandService {

    @RequestMapping("/command/issuance")
    R<String> issuance(@RequestBody String json);

}
