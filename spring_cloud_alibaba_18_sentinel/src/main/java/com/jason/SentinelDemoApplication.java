package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author：yangwushuo
 * @time：2022/10/22 11:10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelDemoApplication.class,args);
    }
}
