package com.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/test")
    public String index(){
        return "Hello from Netflix BackEnd!";
    }
}
