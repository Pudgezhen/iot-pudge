package com.pudge.cn.iot.system.device.config;



import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/4/6 11:21
 */
@Service
public class PuMQTTProvider {
    @Autowired
    private MqttAsyncClient MqttClient;

    public boolean publish(String topic,String msg,int qos,boolean isRetained) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setQos(qos);
        message.setRetained(isRetained);

        IMqttToken token = MqttClient.publish(topic,message);
        token.waitForCompletion();
        return token.isComplete();
    }

    public String publishAsyn(String topic,String msg,int qos,boolean isRetained) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setQos(qos);
        message.setRetained(isRetained);

        IMqttToken token = MqttClient.publish(topic,message);
        return "已经发送成功:"+token.isComplete();
    }

}
