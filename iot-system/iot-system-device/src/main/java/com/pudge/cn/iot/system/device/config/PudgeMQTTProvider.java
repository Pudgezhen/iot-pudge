package com.pudge.cn.iot.system.device.config;

import org.checkerframework.checker.units.qual.A;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/4/6 11:21
 */
@Service
public class PudgeMQTTProvider {

    @Autowired
    private MqttTopic mqttTopic;

    public String publish(String msg) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        MqttDeliveryToken token;
        token = mqttTopic.publish(message);
        token.waitForCompletion();
        return "已经发送成功:"+token.isComplete();
    }

}
