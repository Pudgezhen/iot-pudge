package com.pudge.cn.iot.system.device.service;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/8/24 9:48
 */
public interface ICommandIssuanceService {


    /**
     *  用于同步发送指令给到设备
     * @return 返回指令下发是否成功
     */
    public boolean commandIssuance(String json);

    /**
     *  用于异步发送指令给到设备
     *
     */
    public void commandIssuanceAsyn();
}
