package com.pudge.cn.iot.system.auth.handler;

import cn.hutool.json.JSONUtil;
import com.pudge.cn.iot.common.golbalResponse.PudResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mu_zhen
 * @description 当未登录或者token失效访问接口时，自定义的返回结果
 * @Date 2023/3/4 9:56
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(PudResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}