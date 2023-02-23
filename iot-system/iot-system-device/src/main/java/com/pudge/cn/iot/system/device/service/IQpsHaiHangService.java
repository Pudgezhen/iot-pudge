package com.pudge.cn.iot.system.device.service;


import java.io.IOException;
import java.util.Map;

/**
 * 提供hsf接口给到web工程，用来查询展示海航货运信息
 */
public interface IQpsHaiHangService {

    Map<String, Object> queryHaiHang(String tranceNos) throws IOException;

}
