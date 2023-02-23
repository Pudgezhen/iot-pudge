package com.pudge.cn.iot.system.user.controller;



import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class TestController {


    @PostMapping("/test")
    public String hello(@RequestParam(value = "name",defaultValue = "muzhen") String name){
        log.info("log 测试--------");
        return "hello "+name;
    }
}
