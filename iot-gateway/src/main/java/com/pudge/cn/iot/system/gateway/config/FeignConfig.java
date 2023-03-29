package com.pudge.cn.iot.system.gateway.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * @author mu_zhen
 * @description  gateway 拦截器中若调用其他服务需添加的配置，暂时无用
 * @Date 2023/3/22 23:32
 */

@Configuration
public class FeignConfig {
//    @Bean
//    @ConditionalOnMissingBean
//    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
//        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
//    }
}

