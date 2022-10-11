package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author：yangwushuo
 * @time：2022/10/11 14:00
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductAppliction {
    public static void main(String[] args) {
        SpringApplication.run(ProductAppliction.class,args);
    }
}
