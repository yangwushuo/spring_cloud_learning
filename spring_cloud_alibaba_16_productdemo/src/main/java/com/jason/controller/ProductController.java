package com.jason.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：yangwushuo
 * @time：2022/10/18 15:41
 */
@RestController
public class ProductController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/product")
    String getProduct(@RequestParam("id") Integer id){
        System.out.println("id:"+id+" 获取商品");
        return "product: xxxx port:"+port;
    }


}
