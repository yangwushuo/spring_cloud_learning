package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author yangwushuo
 * @time 2022/10/15 13:32
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigserverApplication.class,args);
    }
}
