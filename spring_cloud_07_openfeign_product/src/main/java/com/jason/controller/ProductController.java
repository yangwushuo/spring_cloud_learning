package com.jason.controller;

import com.jason.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author：yangwushuo
 * @time：2022/10/11 14:00
 */
@RestController
public class ProductController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/product")
    String getProduct(){
        return "product success port:"+port;
    }

    @GetMapping("/product_list")
    String getProductList(){
        return "product list success port:"+port;
    }

    @GetMapping("/test1")
    String getTest1(@RequestParam("name") String name,@RequestParam("age") Integer age){
        return "name:".concat(name).concat(" age:").concat(age.toString()).concat(" port:").concat(port.toString());
    }

    @GetMapping("/test2/{name}/{age}")
    String getTest2(@PathVariable("name") String name, @PathVariable("age") Integer age){
        return "name:".concat(name).concat(" age:").concat(age.toString()).concat(" port:").concat(port.toString());
    }

    @PostMapping("/test3")
    String getTest3(@RequestBody Product product){
        return product.toString().concat(" port:").concat(port.toString());
    }

    @GetMapping("/test4")
    String getTest4(@RequestParam("ids")String[] ids){
        return "ids size:".concat(String.valueOf(ids.length)).concat(" port:").concat(port.toString());
    }

    @GetMapping("/test5/{id}")
    List<Product> getTest5(@PathVariable("id")Integer id){
        List<Product> products = new ArrayList<>();
        products.add(new Product(id,"jason","男",22));
        products.add(new Product(id,"yangwushuo","男",23));
        products.add(new Product(id,"杨武硕","男",22));
        return products;
    }

    @GetMapping("/test6/{id}")
    Map<String,Object> getTest6(@PathVariable("id")Integer id){
        Map<String,Object> resMap = new HashMap<>();
        List<Product> products = new ArrayList<>();
        products.add(new Product(id,"jason","男",22));
        products.add(new Product(id,"yangwushuo","男",23));
        products.add(new Product(id,"杨武硕","男",22));
        resMap.put("num",products.size());
        resMap.put("data",products);
        return resMap;
    }

}
