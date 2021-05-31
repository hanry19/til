package com.example.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @GetMapping("/test")
    public String hello(){
        return "hello world";
    }
}
