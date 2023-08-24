package com.pudge.cn.iot.api.device.enumeration;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/8/24 9:36
 */

import org.apache.http.annotation.Contract;

/**
 * 用于指明设备操作类型
 */
public enum Operation {

    /**
     * 用于标明开灯操作
     */
    LED_ON(0),

    /**
     * 用于标明关灯操作
     */
    LED_OFF(1);
    private int code;

    Operation(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }


}
