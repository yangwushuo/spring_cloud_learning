package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author：yangwushuo
 * @time：2022/10/18 15:38
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserDemoApplication.class,args);
    }
}
