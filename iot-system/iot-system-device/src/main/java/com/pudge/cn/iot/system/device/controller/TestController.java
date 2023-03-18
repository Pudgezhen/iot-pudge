package com.pudge.cn.iot.system.device.controller;


import com.alibaba.fastjson.JSONObject;
import com.pudge.cn.iot.api.device.pojo.HaiHang;
import com.pudge.cn.iot.api.device.pojo.QpsHaiHangVo;
import com.pudge.cn.iot.api.user.services.TestService;
import com.pudge.cn.iot.common.utils.excel.operation.ExcelOperation;
import com.pudge.cn.iot.system.device.service.IQpsHaiHangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@RestController
@RequestMapping
@RefreshScope
@Api(value = "测试接口", tags = "设备管理接口")
public class TestController {

    @Autowired
    private TestService testService;



    @Value("${iot.name}")
    private String name;

    @GetMapping("/test")
    @ApiOperation(value = "测试获取", notes = "测试Get")
    public String test() {

        return testService.test(name);
    }

//    @RequestMapping("/testExcel")
//    @ApiOperation(value = "测试下载Excel", notes = "测试下载Excel")
//    public String testExcel(HttpServletResponse globalResponse, @RequestParam String a) throws IllegalAccessException, IOException {
//
//        Map<String, Object> map = qpsHaiHangService.queryHaiHang(a);
//        Map<String, List<Object>> mapList = new HashMap<>();
//        Set<String> strs = map.keySet();
//        for (String key : strs) {
//            mapList.put(key, (List<Object>) map.get(key));
//        }
//        String json = "{\"HUM72012242\":[{\"flight\":\"离港航班：HU7025/3月31日/HAK-SZX\",\"goodsVolumn\":\"34.30\",\"station\":\"HAK(海口)\",\"time\":\"2022-03-31T22:50:19\",\"operation\":\"DEP(已离港)\",\"goodsNum\":\"11\"},{\"flight\":\"离港航班：HU7021/3月31日/HAK-SZX\",\"goodsVolumn\":\"34.30\",\"station\":\"HAK(海口)\",\"time\":\"2022-03-31T12:51:44\",\"operation\":\"DEP(已离港)\",\"goodsNum\":\"11\"}]}\n";
//        HaiHang haihang = JSONObject.parseObject(json, HaiHang.class);
//        System.out.println(haihang);
//        HSSFWorkbook workbook = ExcelOperation.exportExcelSheets(QpsHaiHangVo.class, mapList, null);
//        globalResponse.setHeader("Content-disposition", "attachment;filename=test.xls");
//        globalResponse.setContentType("application/vnd.ms-excel");
//        OutputStream os = globalResponse.getOutputStream();
//        workbook.write(os);
//        os.flush();
//        os.close();
//        return "ok";
//    }
}
