package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author：yangwushuo
 * @time：2022/10/12 22:20
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OepnfeignHyStrixDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OepnfeignHyStrixDemoApplication.class,args);
    }
}
