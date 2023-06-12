//package com.pudge.cn.iot.system.device.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.eclipse.paho.mqttv5.client.*;
//import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
//import org.eclipse.paho.mqttv5.common.MqttException;
//import org.eclipse.paho.mqttv5.common.MqttMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//
///**
// * @author mu_zhen
// * @description
// * @Date 2023/4/6 11:04
// */
//@Component
//@Slf4j
//public class PudgeMQTTClient {
//
//    @Autowired
//    private MQTTConfig mqttConfig;
//
//    @Autowired
//    private PuCallBack puCallBack;
//
//
//    @Bean
//    public MqttAsyncClient createMQTTClient(MqttConnectionOptions connOpts) throws MqttException {
//        //内存的持久化 可以选择文件的持久化
//        MemoryPersistence persistence = new MemoryPersistence();
//
//        MqttAsyncClient MqttAsyncClient =  new MqttAsyncClient(mqttConfig.getBroker(),mqttConfig.getClientId(),persistence);
//        MqttAsyncClient.setCallback(puCallBack);
//        IMqttToken token = MqttAsyncClient.connect(connOpts);
//        token.waitForCompletion();
//        log.info("mqtt is connected......");
//        MqttAsyncClient.subscribe(mqttConfig.getTopic(), 0);
//        return MqttAsyncClient;
//    }
//
//    @Bean
//    public MqttConnectionOptions createMqttConnectOptions(){
//        MqttConnectionOptions connOpts = new MqttConnectionOptions();
//        // 在重新启动和重新连接时记住状态 TODO 后续灵活添加更多设置
//        connOpts.setCleanStart(false);
//        connOpts.setWill("disconnect",new MqttMessage(mqttConfig.getClientId().getBytes(StandardCharsets.UTF_8)));
//        return connOpts;
//    }
//
//
//
//}
