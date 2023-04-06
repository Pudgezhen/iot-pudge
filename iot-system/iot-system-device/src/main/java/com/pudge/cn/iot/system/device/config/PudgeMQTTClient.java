package com.pudge.cn.iot.system.device.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.stereotype.Component;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/4/6 11:04
 */
@Component
public class PudgeMQTTClient {

    @Autowired
    private MQTTConfig mqttConfig;

    @Bean
    public MqttTopic createMQTTClient(MqttConnectOptions connOpts) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();

        MqttClient mqttClient =  new MqttClient(mqttConfig.getBroker(),mqttConfig.getClientId(),persistence);
        mqttClient.connect(connOpts);
        MqttTopic mqttTopic = mqttClient.getTopic(mqttConfig.getTopic());
        return mqttTopic;
    }

    @Bean
    public MqttConnectOptions createMqttConnectOptions(){
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // 在重新启动和重新连接时记住状态 TODO 后续灵活添加更多设置
        connOpts.setCleanSession(false);
        return connOpts;
    }



}
