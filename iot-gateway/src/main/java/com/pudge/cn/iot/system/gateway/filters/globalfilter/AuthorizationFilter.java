package com.pudge.cn.iot.system.gateway.filters.globalfilter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.pudge.cn.iot.api.auth.entity.Permission;
import com.pudge.cn.iot.common.redis.RedisService;
import com.pudge.cn.iot.common.constant.AuthConstant;
import com.pudge.cn.iot.common.entity.JwtAuthPayload;
import com.pudge.cn.iot.common.utils.jwt.JwtToken;
import com.pudge.cn.iot.system.gateway.config.IgnoreUrlsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author mu_zhen
 * @description 权限认证
 * @Date 2023/3/16 10:10
 */
@Slf4j
@Component
public class AuthorizationFilter implements Ordered, GlobalFilter {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 白名单校验
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除JWT请求头
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                request = exchange.getRequest().mutate().header(AuthConstant.JWT_TOKEN_HEADER, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        // 权限校验
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        // 对于 免登录的 页面 url 已在白名单校验，所有先判断token
        if (StrUtil.isEmpty(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //从token中解析用户信息并设置到Header中去
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        JwtAuthPayload payload = null;
        try {
            payload = JwtToken.parseToken(realToken);
        }catch (TokenExpiredException e){
            log.error("Token: " + e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String userStr = JSONObject.toJSONString(payload);
        log.info("AuthGlobalFilter.filter() user:{}",userStr);
        String ip = exchange.getRequest().getRemoteAddress().getHostString();
        log.info("获取的ip："+ ip);
        // 防止登录之后的token被盗用，加一层ip校验
        if (!ip.equals(payload.getIp())){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 对请求做权限校验
        if(!hasRolePermission(exchange,payload)){
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // 验证成功后即将路由转发，将用户信息封装在请求头
        request = exchange.getRequest().mutate().header(AuthConstant.USER_TOKEN_HEADER, userStr).build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }



    @Override
    public int getOrder() {
        return 0;
    }



    /**
     * 完全匹配校验
     * @param permissionsMatch0
     * @param uri
     * @param method
     * @param serviceName
     * @return
     */
    private Permission matchUri(List<Permission> permissionsMatch0
                                        ,String uri,String method
                                        ,String serviceName){

        PathMatcher pathMatcher = new AntPathMatcher();

        for (Permission permission : permissionsMatch0) {
            String matchUrl = permission.getUrl();
            String matchMethod = permission.getMethod();
            if(pathMatcher.match(matchUrl,uri)){
                // 提交方式和服务匹配
                if(method.equals(matchMethod) && serviceName.equals(permission.getServiceName())){
                    return permission;
                }
            }
        }
        return null;
    }

    /**
     * 对请求做权限校验  AntPathMatcher
     * @param exchange
     * @param userInfo
     * @return
     */
    private Boolean hasRolePermission(ServerWebExchange exchange,JwtAuthPayload userInfo){
        ServerHttpRequest request = exchange.getRequest();
        //获取uri
        String uri = request.getURI().getPath();
        // 获取Method
        String method = request.getMethodValue();
        // 获取服务名字
        Route router = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String serviceName = router.getId();

        String[] roles = userInfo.getRoleid().split(",");
        Permission permission = null;
        for (String role : roles) {
            Set<Permission> permissionSet = (Set<Permission>) redisService.hGet("RolePermissionMap",AuthConstant.Auth_Absolutely_Match+role);

            if (null == permissionSet){
                continue;
            }
            List<Permission> permissions = new ArrayList<>(permissionSet);
            permission = matchUri(permissions,uri,method,serviceName);
            if(permission!=null){
                break;
            }
        }
        return permission!=null;
    }
}
