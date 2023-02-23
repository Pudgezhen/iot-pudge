package com.pudge.cn.iot.api.user.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("iot-user")
public interface TestService {

    @PostMapping("/user/test")
    String test(@RequestParam("name") String name);
}
