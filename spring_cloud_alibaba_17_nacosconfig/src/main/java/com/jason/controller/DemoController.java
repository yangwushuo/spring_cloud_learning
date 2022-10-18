package com.jason.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：yangwushuo
 * @time：2022/10/18 16:43
 */
@RestController
@RefreshScope //此注解功能: 当nacos配置文件动态刷新时,自动更新当前作用域中与配置文件键值对有管理得属性
public class DemoController {

    @Value("${name}")
    private String name;

    @GetMapping("/demo")
    String getName(){
        return "name: "+name;
    }

}
