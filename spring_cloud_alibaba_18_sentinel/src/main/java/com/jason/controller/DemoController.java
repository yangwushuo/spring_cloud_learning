package com.jason.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：yangwushuo
 * @time：2022/10/22 11:10
 */
@RestController
public class DemoController {

    @GetMapping("/demo")
    String getDemo(){
        return "这是demo!!!";
    }

}
