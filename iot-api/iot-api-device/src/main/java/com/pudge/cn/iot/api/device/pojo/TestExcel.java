package com.pudge.cn.iot.api.device.pojo;

import com.pudge.cn.iot.common.utils.excel.annotation.EnableExport;
import com.pudge.cn.iot.common.utils.excel.annotation.EnableExportField;
import lombok.Data;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/2/20 14:48
 */
@EnableExport(fileName = "测试")
@Data
public class TestExcel {

    @EnableExportField(colName = "姓名")
    private String name;
    @EnableExportField(colName = "住址")
    private String addr;
    @EnableExportField(colName = "性别")
    private String sex;

}
