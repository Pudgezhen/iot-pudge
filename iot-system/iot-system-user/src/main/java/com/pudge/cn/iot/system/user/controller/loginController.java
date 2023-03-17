package com.pudge.cn.iot.system.user.controller;

import com.pudge.cn.iot.api.user.entity.UserInfo;
import com.pudge.cn.iot.common.response.PudResult;
import com.pudge.cn.iot.common.utils.jwt.JwtToken;
import com.pudge.cn.iot.system.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
        UserInfo userInfo = userInfoService.getById(username);
        if (userInfo == null){
            return PudResult.failed("此用户不存在");
        }
        // 密码校验
        if (!password.equals(userInfo.getPassword())){
            return PudResult.failed("密码不正确");
        }
        String ip = request.getRemoteAddr();
        // 封装Jwt
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("ip",ip);
        return PudResult.success(JwtToken.createToken(map));
    }


}
