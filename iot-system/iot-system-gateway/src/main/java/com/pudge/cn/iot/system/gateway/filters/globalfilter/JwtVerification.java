package com.pudge.cn.iot.system.gateway.filters.globalfilter;

import com.pudge.cn.iot.common.cache.RedisService;
import com.pudge.cn.iot.common.constant.AuthConstant;
import com.pudge.cn.iot.common.utils.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
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
        URI uri = exchange.getRequest().getURI();
        String url = uri.getHost();
        log.info("获取到的url: "+ url);
        if ("/login/do".equals(url) || "/userInfo/register".equals(url)){
            return chain.filter(exchange);
        }
        String jwt = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        log.info("jwt:"+jwt);
        Map<String,Object> jwtParam = JwtToken.parseToken(jwt);
        String ip = new String(exchange.getRequest().getRemoteAddress().getAddress().getAddress());
        log.info("获取的ip："+ ip);
        if (ip.equals(jwtParam.get("ip"))
                && redisService.get((String) jwtParam.get(AuthConstant.JWT_REDIS_PREFIX+"username"))
                    .equals(jwtParam.get("password"))){
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }



    @Override
    public int getOrder() {
        return 0;
    }


}
