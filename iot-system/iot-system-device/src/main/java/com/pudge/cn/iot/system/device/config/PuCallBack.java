package com.pudge.cn.iot.system.device.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.stereotype.Component;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/4/7 14:14
 */
@Component
@Slf4j
public class PuCallBack implements MqttCallback {
    @Override
    public void disconnected(MqttDisconnectResponse disconnectResponse) {
        log.info("Mqtt 消费者断开连接："+ disconnectResponse.getReasonString());
    }

    @Override
    public void mqttErrorOccurred(MqttException exception) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        log.info(topic+"  消息到达："+ new String(message.getPayload()));

    }

    @Override
    public void deliveryComplete(IMqttToken token) {

    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {

    }

    @Override
    public void authPacketArrived(int reasonCode, MqttProperties properties) {

    }
}
