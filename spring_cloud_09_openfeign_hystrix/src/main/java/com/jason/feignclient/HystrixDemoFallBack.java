package com.jason.feignclient;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author：yangwushuo
 * @time：2022/10/12 22:24
 */
@Component
public class HystrixDemoFallBack implements HystrixDemo{
    @Override
    public String testHyStrix(Integer id) {
        return "测试失败";
    }
}
