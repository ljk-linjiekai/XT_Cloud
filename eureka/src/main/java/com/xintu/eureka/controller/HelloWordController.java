package com.xintu.eureka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HelloWordController {

    @RequestMapping("/helloword")
     public  String helloword(){
         return "helloword";
     }
}
