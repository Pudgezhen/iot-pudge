package com.pudge.cn.iot.api.user.feign;

import com.pudge.cn.iot.api.user.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mu_zhen
 * @description Excel 操作工具类
 * @Date 2023/3/22 22:36
 */
@Component
@FeignClient("iot-user")
@RequestMapping("/userInfo")
public interface UserService {

    @GetMapping("getUser")
    UserInfo getUser(@RequestParam("username") String username);
}
