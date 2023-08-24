package com.pudge.cn.iot.api.device.entity;

import lombok.Data;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/8/24 10:15
 */
@Data
public class Command {
    private String deviceId;

    private int operate;
}
