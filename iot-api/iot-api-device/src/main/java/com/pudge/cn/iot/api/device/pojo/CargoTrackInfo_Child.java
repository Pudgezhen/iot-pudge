package com.pudge.cn.iot.api.device.pojo;


import lombok.Data;

/**
 * 货物跟踪记录
 */
@Data
public class CargoTrackInfo_Child {


    /**
     * 获取跟踪记录ID
     */
    private String CargoTrackID;

    /**
     *站点三字码
     */
    private String Station;

    /**
     *站点名称
     */
    private String Station_Name;

    /**
     *操作时间
     */
    private String HandleTime;

    /**
     *状态码
     */
    private String StateID;

    /**
     *状态名称
     */
    private String StateName;

    /**
     *航班号
     */
    private String FlightNO;

    /**
     * 航班日期
     */
    private String FlightDate;

    /**
     *起飞机场三字码
     */
    private String StartAirport;

    /**
     *起飞机场名称
     */
    private String StartAirport_Name;

    /**
     *到达机场三字码
     */
    private String EndAirport;

    /**
     *到达机场名称
     */
    private String EndAirport_Name;

    /**
     * 实际起飞时间
     */
    private String TakeOff;

    /**
     *实际到达时间
     */
    private String Landing;

    /**
     *件数
     */
    private String CT_GoodsNum;

    /**
     *重量
     */
    private String CT_GoodsWeight;

    /**
     *是否从电报获取跟踪记录
     */
    private String IsTelegraph;

    /**
     *货物跟踪电报内容1
     */
    private String CTelegramInfo1;

    /**
     *货物跟踪电报内容2
     */
    private String CTelegramInfo2;

    /**
     *英文货物跟踪电报内容1
     */
    private String ETelegramInfo1;

    /**
     *英文货物跟踪电报内容2
     */
    private String ETelegramInfo2;
}
