package com.pudge.cn.iot.system.monitor;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author mu_zhen
 * @description
 * @Date 2023/3/8 13:58
 */


@SpringBootApplication
@EnableAdminServer
public class MonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class,args);
    }
}
