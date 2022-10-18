package com.jason.controller;

import com.jason.feignclient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：yangwushuo
 * @time：2022/10/18 15:41
 */
@RestController
public class UserController {

    private ProductClient productClient;

    @Autowired
    public UserController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/user")
    String getUser(@RequestParam("id") Integer id){
        String product = productClient.getProduct(id);
        return "user has product:"+product;
    }

}
