package com.jason.controller;

import com.jason.feignclient.HystrixDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：yangwushuo
 * @time：2022/10/12 22:22
 */
@RestController
public class DemoController {

    private HystrixDemo hystrixDemo;

    @Autowired
    public DemoController(HystrixDemo hystrixDemo) {
        this.hystrixDemo = hystrixDemo;
    }

    @GetMapping("test_openfeign_hystrix")
    String getDemo(@RequestParam("id") Integer id){
        System.out.println("test_openfeign_hystrix ok!!!");
        String result = hystrixDemo.testHyStrix(id);
        return "test_openfeign_hystrix ok!!! res:"+result;
    }

}
