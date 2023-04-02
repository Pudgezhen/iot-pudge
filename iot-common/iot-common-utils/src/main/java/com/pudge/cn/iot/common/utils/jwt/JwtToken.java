package com.pudge.cn.iot.common.utils.jwt;


import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pudge.cn.iot.common.entity.JwtAuthPayload;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author mu_zhen
 * @description Excel 操作工具类
 * @Date 2023/3/13 22:51
 */
// TODO 需要增加刷新令牌的方法
public class JwtToken {
    //默认秘钥
    private static final String DEFAULT_SECRET="iotPudgeJwtDefaultSecret";

    /***
     * 生成令牌
     * @param payload
     * @return
     */
    public static String createToken(JwtAuthPayload payload){
        return createToken(payload,null);
    }

    /***
     * 生成令牌
     * @return
     */
    public static String createToken(JwtAuthPayload payload,String secret){
        //秘钥为空就采用默认秘钥
        if(StringUtils.isEmpty(secret)){
            secret = DEFAULT_SECRET;
        }

        //创建令牌操作算法
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //创建令牌
        return JWT.create()
                .withClaim("body", JSONObject.parseObject(JSONObject.toJSONString(payload),Map.class))
                .withIssuer("pudge")            //JWT签发者
                .withSubject("JWT令牌")       //主题
                .withAudience("member")      //接收JWT的一方
                .withExpiresAt(new Date(System.currentTimeMillis()+3600000))    //过期时间   ms
                .withNotBefore(new Date(System.currentTimeMillis()))      //指定时间之前JWT令牌是不可用的
                .withIssuedAt(new Date())    //JWT签发时间
                .withJWTId(UUID.randomUUID().toString().replace("-","")) // jwt唯一标识
                .sign(algorithm);
    }

    /***
     * 解析令牌
     * @param token
     * @return
     */
    public static JwtAuthPayload parseToken(String token){
        return parseToken(token,null);
    }

    /***
     * 令牌校验并解析
     * @param token
     * @return
     */
    public static JwtAuthPayload parseToken(String token,String secret) throws TokenExpiredException {
        //秘钥为空就采用默认秘钥
        if(StringUtils.isEmpty(secret)){
            secret = DEFAULT_SECRET;
        }
        //确认签名算法
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //创建令牌校验对象
        JWTVerifier verifier = JWT.require(algorithm).build();
        //校验解析
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("body").as(JwtAuthPayload.class);
    }

    /***
     * 令牌校验解析
     * @param args
     */
//    public static void main(String[] args) {
//        JwtAuthPayload payload = new JwtAuthPayload();
//        payload.setIp("127.0.0.1");
//        payload.setRoleid("1,2");
//        payload.setUsername("王桢");
//
//
//
//        //创建令牌
//        String token = createToken(payload);
//        System.out.println(token);
//
//        //解析令牌
//        JwtAuthPayload jwtAuthPayload =parseToken(token);
//        System.out.println(JSONObject.toJSONString(jwtAuthPayload));
//    }
}
