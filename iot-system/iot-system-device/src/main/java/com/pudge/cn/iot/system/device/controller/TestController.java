package com.pudge.cn.iot.system.device.controller;




import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.pudge.cn.iot.api.user.feign.UserService;
import com.pudge.cn.iot.common.response.R;
import com.pudge.cn.iot.system.device.config.PuMQTTProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


@RestController
@RefreshScope
@Api(value = "测试接口", tags = "设备管理接口")
@Slf4j
@RequestMapping()
public class TestController {

    @Autowired
    private PuMQTTProvider puMQTTProvider;
    @Autowired
    private UserService userService;

    private boolean b = true;

    @Value("${iot.name}")
    private String name;

    @SentinelResource(value = "test",entryType = EntryType.IN,blockHandler = "blockHandler",fallback = "fallback")
    @GetMapping("/test")
    @ApiOperation(value = "测试获取", notes = "测试Get")
    public R test() throws MqttException {
//        while (b){userService.getUser(name);}

//        return R.success(userService.getUser(name));
        return R.success(puMQTTProvider.publish("light","hahah",0,false));
    }

    public R blockHandler(BlockException ex){
        log.info("blockHandler" + ex.getRule());
        return R.fail(ex.getMessage());
    }

    public R fallback(){
        log.info("fallback=======================>");
        return R.fail("fallback");
    }
}
