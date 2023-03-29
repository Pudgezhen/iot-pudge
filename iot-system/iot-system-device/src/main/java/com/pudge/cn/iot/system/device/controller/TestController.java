package com.pudge.cn.iot.system.device.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RefreshScope
@Api(value = "测试接口", tags = "设备管理接口")
public class TestController {




    @Value("${iot.name}")
    private String name;

    @GetMapping("/test")
    @ApiOperation(value = "测试获取", notes = "测试Get")
    public String test() {

        return "ok"+name;
    }

}
