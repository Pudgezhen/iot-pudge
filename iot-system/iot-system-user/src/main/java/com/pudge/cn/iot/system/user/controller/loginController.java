package com.pudge.cn.iot.system.user.controller;

import com.pudge.cn.iot.common.response.PudResult;
import com.pudge.cn.iot.system.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/3/15 16:18
 */
@RestController
@RequestMapping("/login")
public class loginController {

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/do")
    public PudResult doLogin(String username, String password,
                             HttpServletRequest request){
        // 查询数据库用户信息

        // 密码校验

        // 获取ip

        // 封装Jwt

        return PudResult.failed();

    }
}
