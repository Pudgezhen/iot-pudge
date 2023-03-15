package com.pudge.cn.iot.system.user.controller;


import com.pudge.cn.iot.api.user.entity.UserInfo;
import com.pudge.cn.iot.common.response.PudResult;
import com.pudge.cn.iot.system.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/register")
    public PudResult register(@RequestBody UserInfo userInfo){
        userInfo.setCreated(new Date());
        userInfo.setUpdated(new Date());
        boolean flag = userInfoService.save(userInfo);
        if (flag){
            return PudResult.success("注册成功");
        }else{
            //TODO  添加 用户信息的 校验 以及 密码的 MD5加密
            return PudResult.failed();
        }
    }

    @RequestMapping("/modify")
    public PudResult modify(@RequestBody UserInfo userInfo){
        userInfo.setUpdated(new Date());
        boolean flag = userInfoService.updateById(userInfo);
        if (flag){
            return PudResult.success("注册成功");
        }else{
            //TODO  添加 用户信息的 校验 以及 密码的 MD5加密
            return PudResult.failed();
        }
    }
}
