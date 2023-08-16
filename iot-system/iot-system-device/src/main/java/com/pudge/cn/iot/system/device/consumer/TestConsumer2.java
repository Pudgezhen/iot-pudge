package com.pudge.cn.iot.system.device.consumer;

import com.pudge.mqtt5.annotation.MQTTConsumer;
import com.pudge.mqtt5.inter.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.common.MqttMessage;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/6/1 9:28
 */
@MQTTConsumer(topic = "test2")
@Slf4j
public class TestConsumer2 implements ConsumerService {
    @Override
    public void receive(MqttMessage message) {
        log.info("TestConsumer收到消息：" +new String(message.getPayload()));
    }
}
