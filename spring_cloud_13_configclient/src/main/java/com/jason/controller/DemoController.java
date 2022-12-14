package com.jason.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwushuo
 * @time 2022/10/15 19:27
 */
@RestController
//添加RefreshScope注解可以动态刷新类中使用配置文件的字段数值
@RefreshScope
public class DemoController {

    @Value("${name}")
    String name;

    @GetMapping("/demo")
    String getName(){
        System.out.println("开始调用demo接口");
        return name;
    }

}
