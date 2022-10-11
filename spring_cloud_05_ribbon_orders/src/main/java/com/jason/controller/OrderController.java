package com.jason.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangwushuo
 * @time 2022/10/10 14:50
 */
@RestController
public class OrderController {

    private RestTemplate restTemplate;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/order")
    public String getOrder(){
        return "success order port:"+port;
    }

}
