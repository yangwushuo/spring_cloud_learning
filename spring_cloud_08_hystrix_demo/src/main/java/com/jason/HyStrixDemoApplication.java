package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author：yangwushuo
 * @time：2022/10/12 21:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker //开启熔断服务
public class HyStrixDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HyStrixDemoApplication.class,args);
    }
}
