package com.jason.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author yangwushuo
 * @time 2022/10/10 14:49
 */
@RestController
public class UserController {

    private RestTemplate restTemplate;

    /*获取注册中心指定服务的主机列表
    服务发现客户端对象 根据服务id去服务注册中心获取对应服务列表到本地中 缺点:没有负载均衡 需要自己实现负载均衡*/
    private DiscoveryClient discoveryClient;

    /*负载均衡获取主机列表
    负载均衡客户端对象 根据服务id去服务注册中心获取对应服务列表，根据默认负载均衡策略选择列表中一台机器进行返回
    缺点:使用时需要每次先根据服务id获取一个负载均衡机器之后在通过restTemplate调用服务*/
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    public UserController(RestTemplate restTemplate, DiscoveryClient discoveryClient, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
    }

    @GetMapping("/user")
    public String  getUser(){
        //1一般调用方式
        //String res = restTemplate.getForObject("http://localhost:9916/order", String.class);

        //2获取主机列表调用方式
        //List<ServiceInstance> orders = discoveryClient.getInstances("Orders");
        //orders.forEach((serviceInstance -> {
        //    System.out.println(serviceInstance.getUri());
        //}));

        //3使用负载均衡调用方式
        //ServiceInstance serviceInstance = loadBalancerClient.choose("Orders");

        //4配置restTemplate时添加注解@LoadBalanced调用方式 让restTemplate具备负载LoadBalancerClient功能集成在restTemplate上 之后restTemplate会识别主机名为微服务注册的实例名
        String res = restTemplate.getForObject("http://ORDERS/order", String.class);
        return "success user order:"+res;
    }

}
