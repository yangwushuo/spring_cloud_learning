package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author：yangwushuo
 * @time：2022/10/13 14:01
 */
@SpringBootApplication
@EnableDiscoveryClient //默认引入依赖时, 隐式开启此服务注解
@EnableHystrixDashboard //激活HystrixDashbord组件用于可视化熔断服务
public class HystrixDashBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashBoardApplication.class,args);
    }
}
