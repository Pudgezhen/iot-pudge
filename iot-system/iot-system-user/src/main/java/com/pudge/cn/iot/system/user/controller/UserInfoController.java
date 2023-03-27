package com.pudge.cn.iot.system.user.controller;


import com.pudge.cn.iot.api.user.entity.UserInfo;
import com.pudge.cn.iot.common.constant.AuthConstant;
import com.pudge.cn.iot.common.response.PudResult;
import com.pudge.cn.iot.common.response.R;
import com.pudge.cn.iot.common.utils.encipher.MD5Utils;
import com.pudge.cn.iot.system.user.service.IUserInfoService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R register(@RequestBody UserInfo userInfo) throws Exception {
        userInfo.setCreated(new Date());
        userInfo.setUpdated(new Date());
        userInfo.setPassword(MD5Utils.getMD5Str(userInfo.getPassword()+ AuthConstant.MD5_DEFAULT_SALT));
        boolean flag = userInfoService.save(userInfo);
        if (flag){
            return R.success("注册成功");
        }else{
            return R.fail("注册失败");
        }
    }

    @RequestMapping("/modify")
    public R modify(@RequestBody UserInfo userInfo){
        userInfo.setUpdated(new Date());
        boolean flag = userInfoService.updateById(userInfo);
        if (flag){
            return R.success("注册成功");
        }else{
            //TODO  添加 用户信息的 校验 以及 密码的 MD5加密
            return R.fail("注册失败");
        }
    }

    @GetMapping("getUser")
    public UserInfo getUser(@RequestParam("username") String username){
        return userInfoService.getById(username);
    }
}
