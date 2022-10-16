package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangwushuo
 * @time 2022/10/15 19:07
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigclientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigclientApplication.class,args);
    }
}
