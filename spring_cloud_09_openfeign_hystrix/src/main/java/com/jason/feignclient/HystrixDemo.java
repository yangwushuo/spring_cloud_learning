package com.jason.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author：yangwushuo
 * @time：2022/10/12 22:21
 */

/*
* 使用fallback如果对应服务被降级无法访问 好处:可以不用产生报错,使用默认返回值返回给调用方
* fallback中 继承当前接口的类 实现对应方法 并已对应方法的返回值为结果进行openfeign的返回
* */
@FeignClient(value = "HYSTRIXDEMO",fallback = HystrixDemoFallBack.class)
public interface HystrixDemo {

    @GetMapping("/test_hystrix")
    String testHyStrix(@RequestParam("id") Integer id);

}
