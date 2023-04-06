package com.pudge.cn.iot.system.device.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/4/6 10:52
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="mqtt.provider")
@RefreshScope
public class MQTTConfig {
    private String topic;

    private String Broker;

    private String clientId;
}
