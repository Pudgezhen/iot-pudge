package com.pudge.cn.iot.system.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
public class GatewayApplication {

    public static void main(String[] args)  {

        SpringApplication.run(GatewayApplication.class, args);
    }
}
