package com.jason.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangwushuo
 * @time 2022/10/10 15:01
 */
@Configuration
public class BeansConfig {

    //创建restTemplate实例
    //使用@LoadBalanced + RestTemplate负载均衡客户端注解 修饰范围:用在方法上 作用:让当前方法 当前对象具有ribbon组件负载均衡特性
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }

}
