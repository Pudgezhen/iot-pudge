package com.pudge.cn.iot.common.constant;

/**
 * 权限相关常量定义
 * Created by macro on 2020/6/19.
 */
public interface AuthConstant {

    /**
     * 完全匹配的权限前缀
     */
    String Auth_Absolutely_Match = "Auth0:";

    /**
     * 通配符匹配的权限前缀
     */
    String Auth_Wildcard_Match = "Auth1:";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "user";

    /**
     *  用户JWT令牌在redis的前缀
     */
    String JWT_REDIS_PREFIX = "jwt:";
}
