package com.wp.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @GetMapping("/test")
    public String getToken() {
        return "Testing successful" ;
    }
}
