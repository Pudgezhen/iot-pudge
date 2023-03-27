package com.pudge.cn.iot.system.user.controller;

import com.pudge.cn.iot.api.user.entity.UserInfo;
import com.pudge.cn.iot.common.constant.AuthConstant;
import com.pudge.cn.iot.common.entity.JwtAuthPayload;
import com.pudge.cn.iot.common.response.R;
import com.pudge.cn.iot.common.utils.encipher.MD5Utils;
import com.pudge.cn.iot.common.utils.jwt.JwtToken;
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
    public R doLogin(String username, String password,
                     HttpServletRequest request) throws Exception {
        // 查询数据库用户信息
        UserInfo userInfo = userInfoService.getById(username);
        if (userInfo == null){
            return R.fail("此用户不存在");
        }
        // 密码校验
        if (!MD5Utils.getMD5Str(password+ AuthConstant.MD5_DEFAULT_SALT).equals(userInfo.getPassword())){
            return R.fail("密码不正确");
        }
        String ip = request.getRemoteAddr();
        // 封装Jwt
        JwtAuthPayload payload = new JwtAuthPayload();
        payload.setUsername(username);
        payload.setRoleid(userInfo.getRoleid());
        payload.setIp(ip);
        return R.success(JwtToken.createToken(payload));
    }


}
