package com.jason.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：yangwushuo
 * @time：2022/10/12 21:27
 */
@RestController
public class DemoController {

    /*
    * 当熔断机制检测到接口报错溢出频率 10s 超过 20次 或者 10s 百分之50的报错 则会开启熔断机制
    * 开启熔断机制之后的接口调用都会使用fallbackMethod中自定义的返回进行数据返回
    * 熔断机制开启之后会进行5s的沉默时间,之后再有一次进行,会尝试调用熔断过的接口,如果调用成功则会认为此接口不需要熔断可以正常使用
    * 如果5s后还是报错,继续进行5s沉默,沉默时间内的所有调用都会走自定义返回
    * */

    @GetMapping("/test_hystrix")
    @HystrixCommand(fallbackMethod = "testHyStrixFallBack") //添加熔断后 自定义返回 此接口抛出异常则会调用自定义返回方法
//    @HystrixCommand(defaultFallback = "defaultFallBack") // 熔断后默认返回
    String testHyStrix(@RequestParam("id") Integer id){
        System.out.println("test ok"+System.currentTimeMillis());
        if(id < 0){
            throw new RuntimeException("运行出错");
        }
        return "test ok!!!!";
    }

    String testHyStrixFallBack(Integer id){
        return "服务器繁忙请稍后重试";
    }

    String defaultFallBack(){
        return "默认错误";
    }

}
