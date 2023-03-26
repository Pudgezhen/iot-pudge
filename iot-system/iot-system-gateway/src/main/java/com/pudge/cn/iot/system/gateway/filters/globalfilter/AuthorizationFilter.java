package com.pudge.cn.iot.system.gateway.filters.globalfilter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.pudge.cn.iot.api.auth.entity.Auths;
import com.pudge.cn.iot.api.auth.entity.Permission;
import com.pudge.cn.iot.api.auth.feign.AuthsService;
import com.pudge.cn.iot.api.user.entity.UserInfo;
import com.pudge.cn.iot.api.user.feign.UserService;
import com.pudge.cn.iot.common.cache.RedisService;
import com.pudge.cn.iot.common.constant.AuthConstant;
import com.pudge.cn.iot.common.utils.jwt.JwtToken;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author mu_zhen
 * @description 权限认证
 * @Date 2023/3/16 10:10
 */
@Slf4j
@Component
public class AuthorizationFilter implements Ordered, GlobalFilter {

    @Autowired
    private AuthsService authsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isEmpty(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        //从token中解析用户信息并设置到Header中去
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        Map<String,Object> map = JwtToken.parseToken(realToken);

        String username = (String) map.get("username");
//        UserInfo userInfo = userService.getUser(username);
//        Auths auths = authsService.getAuths(userInfo.getRoleid());

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

    /**
     *  判断是否具有 访问路径的权限
     * @param exchange
     * @return true:有权限
     */
    private Boolean isAuthorization(ServerWebExchange exchange,UserInfo userInfo){
        ServerHttpRequest request = exchange.getRequest();
        //获取uri
        String uri = request.getURI().getPath();
        // 获取Method
        String method = request.getMethodValue();
        // 获取服务名字
        URI routerUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        String serviceName = routerUri.getHost();
        // 从Redis 进行匹配
            //完全匹配  0
        List<Permission> permissionsMatch0 = (List<Permission>) redisService.hGet("RolePermissionAll","Absolutely");
        if(permissionsMatch0 != null){


        }
            // 通配符匹配 1
        Permission permission = matchAbsolutely(permissionsMatch0,uri,method,serviceName);
        // 如果permission == null ,则执行通配符匹配
        if (permission == null){

        }
        return true;
    }

    /**
     * 完全匹配校验
     * @param permissionsMatch0
     * @param uri
     * @param method
     * @param serviceName
     * @return
     */
    private Permission matchAbsolutely(List<Permission> permissionsMatch0
                                        ,String uri,String method
                                        ,String serviceName){
        for (Permission permission : permissionsMatch0) {
            String matchUrl = permission.getUrl();
            String matchMethod = permission.getMethod();
            if(matchUrl.equals(uri)){
                // 提交方式和服务匹配
                if(matchMethod.equals(method) && serviceName.equals(permission.getServiceName())){
                    return permission;
                }
                if("*".equals(matchMethod)&& serviceName.equals(permission.getServiceName())){
                    return permission;
                }
            }
        }
        return null;
    }

    private Boolean rolePermission(String uri,String method,String serviceName,UserInfo userInfo){
        String[] roles = userInfo.getRoleid().split(",");
        Permission permission = null;
        for (String role : roles) {
            List<Permission> permissions = (List<Permission>) redisService.hGet("RolePermissionMap",AuthConstant.Auth_Absolutely_Match+role);
            if (permissions == null){
                continue;
            }
            permission = matchAbsolutely(permissions,uri,method,serviceName);
            if(permission!=null){
                break;
            }
        }
        //permission==null，通配符方式匹配
        if(permission==null){
            // TODO 通配符匹配
        }
        return permission!=null;
    }
}
