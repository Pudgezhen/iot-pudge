package com.pudge.cn.iot.system.device.consumer;

import com.pudge.mqtt5.annotation.MQTTConsumer;
import com.pudge.mqtt5.inter.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.common.MqttMessage;

import java.nio.charset.StandardCharsets;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/8/25 9:09
 */
@MQTTConsumer(topic = "keys001server")
@Slf4j
public class Keys001Consumer implements ConsumerService {
    @Override
    public void receive(MqttMessage message) {
      log.info("keys001 收到消息:" +new String(message.getPayload(), StandardCharsets.UTF_8));
    }
}
