package com.Chiranjibi.JavaTrading.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "this is home page" ;
    }

    @GetMapping("/api")
    public String secret() {
        return "this is home page" ;
    }
}
