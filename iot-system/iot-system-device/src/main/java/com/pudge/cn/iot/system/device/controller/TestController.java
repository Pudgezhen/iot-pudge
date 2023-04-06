package com.pudge.cn.iot.system.device.controller;



import com.pudge.cn.iot.common.response.R;
import com.pudge.cn.iot.system.device.config.PudgeMQTTProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RefreshScope
@Api(value = "测试接口", tags = "设备管理接口")
public class TestController {

    @Autowired
    private PudgeMQTTProvider pudgeMQTTProvider;



    @Value("${iot.name}")
    private String name;

    @GetMapping("/test")
    @ApiOperation(value = "测试获取", notes = "测试Get")
    public R test() throws MqttException {

        return R.success(pudgeMQTTProvider.publish("hhaha"));
    }

}
