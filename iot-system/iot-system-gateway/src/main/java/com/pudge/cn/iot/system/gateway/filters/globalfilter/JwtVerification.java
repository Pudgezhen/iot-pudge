package com.pudge.cn.iot.system.gateway.filters.globalfilter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.pudge.cn.iot.common.cache.RedisService;
import com.pudge.cn.iot.common.constant.AuthConstant;
import com.pudge.cn.iot.common.utils.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Map;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/3/16 10:10
 */
@Slf4j
@Component
public class JwtVerification implements Ordered, GlobalFilter {

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isEmpty(token)) {
            String hello = (String) redisService.get("hello");
            System.out.println("获取到的redis数据："+hello);
            return chain.filter(exchange);
        }

        //从token中解析用户信息并设置到Header中去
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        Map<String,Object> map = JwtToken.parseToken(realToken);
        String userStr = JSONObject.toJSONString(map);
        log.info("AuthGlobalFilter.filter() user:{}",userStr);
        String ip = new String(exchange.getRequest().getRemoteAddress().getAddress().getAddress());
        log.info("获取的ip："+ ip);
        if (!ip.equals(map.get("ip"))){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        ServerHttpRequest request = exchange.getRequest().mutate().header(AuthConstant.USER_TOKEN_HEADER, userStr).build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }



    @Override
    public int getOrder() {
        return 0;
    }


}
