package com.jason.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jason.entity.Product;
import com.jason.feignclient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author：yangwushuo
 * @time：2022/10/11 13:56
 */
@RestController
public class CategoryController {

    private ProductClient productClient;

    @Autowired
    public CategoryController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/category")
    String getCategory(){
        String product = productClient.getProduct();
        return "get category success and product:"+product;
    }

    @GetMapping("/category_list")
    String getCategoryList(){
        String productList = productClient.getProductList();
        return "get category list success and product list:"+productList;
    }

    @GetMapping("/category_test1")
    String getCategoryTest1(){
        String test1Res = productClient.getTest1("杨武硕", 23);
        return "category_test1:".concat(test1Res);
    }

    @GetMapping("/category_test2")
    String getCategoryTest2(){
        String test2Res = productClient.getTest2("杨武硕", 23);
        return "category_test2:".concat(test2Res);
    }

    @PostMapping("/category_test3")
    String getCategoryTest3(){
        String test3Res = productClient.getTest3(new Product(111, "杨武硕", "男", 23));
        return "category_test3".concat(test3Res);
    }

    @GetMapping("/category_test4")
    String getCategoryTest4(){
        String test4Res = productClient.getTest4(new String[]{"1","2","3"});
        return "category_test4:".concat(test4Res);
    }

    @GetMapping("/category_test5")
    List<Product> getCategoryTest5(){
        List<Product> test5Res = productClient.getTest5(23);
        return test5Res;
    }

    @GetMapping("/category_test6")
    Map<String, Object> getCategoryTest6(){
        Map<String, Object> test6Res = productClient.getTest6(23);
        String res = JSONObject.toJSONString(test6Res);
        //手动解析
        System.out.println(res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        Integer num = JSONObject.parseObject(jsonObject.get("num").toString(), Integer.class);
        List<Product> products = JSONObject.parseArray(jsonObject.get("data").toString(), Product.class);
        System.out.println(num);
        System.out.println(products);

        return test6Res;
    }

}
