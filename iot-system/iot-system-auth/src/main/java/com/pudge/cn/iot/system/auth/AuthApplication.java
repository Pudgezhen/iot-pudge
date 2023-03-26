package com.pudge.cn.iot.system.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mu_zhen
 * @description Excel 操作工具类
 * @Date 2023/3/22 22:30
 */

@SpringBootApplication
@MapperScan("com.pudge.cn.iot.system.auth.mapper")
@EnableFeignClients
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
