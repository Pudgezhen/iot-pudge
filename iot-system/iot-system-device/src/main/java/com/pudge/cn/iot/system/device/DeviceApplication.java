package com.pudge.cn.iot.system.device;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.pudge.cn.iot.api.user.services"})
@EnableAdminServer
public class DeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceApplication.class, args);
    }
}
