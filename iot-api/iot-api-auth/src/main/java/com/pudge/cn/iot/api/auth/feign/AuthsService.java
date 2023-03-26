package com.pudge.cn.iot.api.auth.feign;

import com.pudge.cn.iot.api.auth.entity.Auths;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("iot-auth")
@RequestMapping("/auth")
public interface AuthsService {

    @GetMapping("/getAuths")
    Auths getAuths(@RequestParam("roleId") int roleId);
}
