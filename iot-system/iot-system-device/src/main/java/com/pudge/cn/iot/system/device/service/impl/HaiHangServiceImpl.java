//package com.pudge.cn.iot.system.device.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.pudge.cn.iot.api.device.pojo.CargoTrackInfo_Child;
//import com.pudge.cn.iot.api.device.pojo.HaiHangVo;
//import com.pudge.cn.iot.api.device.pojo.QpsHaiHangVo;
//import com.pudge.cn.iot.system.device.controller.HttpClientSend;
//import com.pudge.cn.iot.system.device.service.IQpsHaiHangService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author mu_zhen
// * @description
// * @Date 2023/2/22 9:47
// */
//@Service
//@Slf4j
//public class HaiHangServiceImpl implements IQpsHaiHangService {
//    @Override
//    public Map<String, Object> queryHaiHang(String tranceNos) throws IOException {
//
//        log.info("海航查询start-0-----------------" + tranceNos);
//        String[] tranceNoArr = tranceNos.split(",");
//        Map<String, Object> map = new HashMap<>();
//        List<QpsHaiHangVo> list = null;
//        for (String str : tranceNoArr) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("awbCode", str);
//            String response = HttpClientSend.sendDataForHaiHang(jsonObject.toString());
//            JSONObject responseMap = JSONObject.parseObject(response);
//            if (!"Success".equals(responseMap.get("status")) || null == responseMap.get("data")) {
//                continue;
//            }
//            HaiHangVo haiHangVo = responseMap.getObject("data", HaiHangVo.class);
//            list = new ArrayList<>();
//            for (CargoTrackInfo_Child child : haiHangVo.getCargoTrackInfo_Child()) {
//                QpsHaiHangVo qpsHaiHangVo = new QpsHaiHangVo();
//                if ("出发".equals(child.getStateName())) {
//                    qpsHaiHangVo.setFlight(child.getFlightNO() + "," + child.getStartAirport_Name() + "-" + child.getEndAirport_Name() + ",开始时间：" + child.getTakeOff() + ",结束时间" + child.getLanding());
//                } else if ("已制单".equals(child.getStateName())) {
//                    qpsHaiHangVo.setFlight("--");
//                } else {
//                    qpsHaiHangVo.setFlight(child.getCTelegramInfo2());
//                }
//                qpsHaiHangVo.setGoodsNum(child.getCT_GoodsNum());
//                qpsHaiHangVo.setGoodsVolumn(child.getCT_GoodsWeight());
//                qpsHaiHangVo.setOperation(child.getStateName());
//                qpsHaiHangVo.setStation(child.getStation());
//                qpsHaiHangVo.setTime(child.getHandleTime());
//                list.add(qpsHaiHangVo);
//            }
//            map.put(str, list);
//        }
//
//        return map;
//    }
//}
