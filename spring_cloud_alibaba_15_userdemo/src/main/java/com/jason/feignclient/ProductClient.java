package com.jason.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author：yangwushuo
 * @time：2022/10/18 15:53
 */
@FeignClient("PRODUCT")
public interface ProductClient {

    @GetMapping("/product")
    String getProduct(@RequestParam("id") Integer id);

}
