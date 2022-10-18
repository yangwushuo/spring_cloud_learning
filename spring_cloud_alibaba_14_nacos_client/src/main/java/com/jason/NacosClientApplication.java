package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author：yangwushuo
 * @time：2022/10/17 21:44
 */
@SpringBootApplication
@EnableDiscoveryClient //激活服务注册 注册到nacos server中 可以省略不写
public class NacosClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class,args);
    }
}
