package com.jason.feignclient;

import com.jason.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author：yangwushuo
 * @time：2022/10/11 14:01
 */
@FeignClient("PRODUCTS")
public interface ProductClient {

    @GetMapping("/product")
    String getProduct();

    @GetMapping("/product_list")
    String getProductList();

    /*
    * 离散值传参
    * openfeign作为一个伪http client使用是必须指定是路径传参(@PathParam 指定路径中占位位置)
    * 还是参数传参(@RequestParam 指定参数名) 默认一个参数使用参数传参,变量名为参数名
    * 对象传参
    * openfeign 使用对象作为参数 使用@ResquestBody将对象作为json格式进行传参
    * 数组集合传参
    * openfeign 使用数组传参会将数组拼接成参数传参 ?ids=1&ids=2&id3=3这种形式 接口会自动识别为一个ids数组形式
    * 如果需要list接收需要定义一个对象来进行接收数组
    * map类型传参 Map<String,Object> 返回map类型Obejct可以手动使用json工具进行解析指定类型
    * */
    @GetMapping("/test1")
    String getTest1(@RequestParam("name") String name, @RequestParam("age") Integer age);

    @GetMapping("/test2/{name}/{age}")
    String getTest2(@PathVariable("name") String name, @PathVariable("age") Integer age);

    @PostMapping("/test3")
    String getTest3(@RequestBody Product product);

    @GetMapping("/test4")
    String getTest4(@RequestParam("ids")String[] ids);

    @GetMapping("/test5/{id}")
    List<Product> getTest5(@PathVariable("id")Integer id);

    @GetMapping("/test6/{id}")
    Map<String,Object> getTest6(@PathVariable("id")Integer id);

}
