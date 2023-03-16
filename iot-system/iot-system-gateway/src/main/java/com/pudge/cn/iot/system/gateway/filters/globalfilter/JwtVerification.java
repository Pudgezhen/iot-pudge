package com.pudge.cn.iot.system.gateway.filters.globalfilter;

import com.pudge.cn.iot.common.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/3/16 10:10
 */
@Slf4j
@Component
public class JwtVerification implements Ordered, GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        String url = uri.getHost();
        log.info("获取到的url: "+ url);
        String jwt = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        log.info("jwt:"+jwt);
        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        return exchange.getResponse().setComplete();
    }



    @Override
    public int getOrder() {
        return 0;
    }


}
