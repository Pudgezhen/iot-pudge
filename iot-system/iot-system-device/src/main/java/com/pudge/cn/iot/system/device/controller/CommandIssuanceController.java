package com.pudge.cn.iot.system.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.pudge.cn.iot.common.response.R;
import com.pudge.cn.iot.system.device.service.ICommandIssuanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mu_zhen
 * @description
 * @Date 2023/8/24 9:25
 */
@RestController
@RequestMapping("command")
public class CommandIssuanceController {

    @Autowired
    private ICommandIssuanceService commandIssuanceService;

    @PostMapping("issuance")
    public R<String> issuance(@RequestBody String json){
        boolean b = commandIssuanceService.commandIssuance(json);
        if (b){
            return R.success("ok");
        }else {
            return R.fail("error");
        }
    }


}
