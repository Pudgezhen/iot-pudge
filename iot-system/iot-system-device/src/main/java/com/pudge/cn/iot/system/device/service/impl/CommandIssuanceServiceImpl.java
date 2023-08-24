package com.pudge.cn.iot.system.device.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pudge.cn.iot.api.device.entity.Command;
import com.pudge.cn.iot.api.device.enumeration.Operation;
import com.pudge.cn.iot.system.device.config.PuMQTTProvider;
import com.pudge.cn.iot.system.device.service.ICommandIssuanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/8/24 9:53
 */
@Service
public class CommandIssuanceServiceImpl implements ICommandIssuanceService {

    @Autowired
    private PuMQTTProvider provider;

    @Override
    public boolean commandIssuance(String json) {
        Command command = JSONObject.parseObject(json, Command.class);
        try {
            if (command.getOperate() == Operation.LED_ON.getCode()) {
                // 开灯操作
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("led", 1);
                boolean b = provider.publish(command.getDeviceId(), jsonObject.toJSONString(), 0, false);
                return b;
            }else if(command.getOperate() == Operation.LED_OFF.getCode()){
                // 关灯操作
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("led", 0);
                boolean b = provider.publish(command.getDeviceId(), jsonObject.toJSONString(), 0, false);
                return b;
            }

        }catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public void commandIssuanceAsyn() {

    }
}
