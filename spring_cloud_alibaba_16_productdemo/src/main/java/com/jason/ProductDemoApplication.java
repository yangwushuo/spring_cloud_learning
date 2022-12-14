package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author：yangwushuo
 * @time：2022/10/18 15:38
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductDemoApplication.class,args);
    }
}
