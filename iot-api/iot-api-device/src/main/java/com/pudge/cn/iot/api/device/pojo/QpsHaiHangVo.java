package com.pudge.cn.iot.api.device.pojo;


import com.pudge.cn.iot.common.utils.excel.annotation.EnableExport;
import com.pudge.cn.iot.common.utils.excel.annotation.EnableExportField;
import lombok.Data;

/**
 * 用于海航货运查询返回数据展示VO
 */
@Data
@EnableExport(fileName = "海航货物轨迹")
public class QpsHaiHangVo {
    /**
     *  站点信息
     */
    @EnableExportField(colName = "站点信息")
    private String station;

    /**
     * 时间
     */
    @EnableExportField(colName = "时间")
    private String time;

    /**
     * 操作
     */
    @EnableExportField(colName = "操作")
    private String operation;

    /**
     *  航班
     */
    @EnableExportField(colName = "航班")
    private String flight;

    /**
     * 件数
     */
    @EnableExportField(colName = "件数")
    private String goodsNum;

    /**
     * 重量
     */
    @EnableExportField(colName = "重量")
    private String goodsVolumn;
}
