package com.pudge.cn.iot.api.device.pojo;

import lombok.Data;

import java.util.List;

/**
 *  封装海航货运返回数据
 */
@Data
public class HaiHangVo {

    /**
     *主运单号
     */
    private String AwbCode;

    /**
     *主运单号前缀
     */
    private String FirstCode;

    /**
     *主运单号后缀
     */
    private String LastCode;

    /**
     *订单起飞机场
     */
    private String Order_StartAirport;

    /**
     * 订单到达机场
     */
    private String Order_EndAirport;

    /**
     * 订单中转机场
     */
    private String Order_TransferStation;

    /**
     * 订单起飞机场三字码
     */
    private String Order_StartAirportCode;

    /**
     *订单到达机场三字码
     */
    private String Order_EndAirportCode;

    /**
     *订单中转机场三字码
     */
    private String Order_TransferStationCode;

    /**
     *航线
     */
    private String FlightLines;

    /**
     *制单计费重
     */
    private String ChargeableWeight;

    /**
     *分单数量
     */
    private String HAWBCount;

    /**
     *件数
     */
    private String GoodsNum;

    /**
     * 重量
     */
    private String GoodsVolumn;

    /**
     *体积
     */
    private String GoodsName;

    /**
     *货物跟踪记录(集合)
     */
    private List<CargoTrackInfo_Child> CargoTrackInfo_Child;
}
