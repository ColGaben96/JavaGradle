package com.example.javagradle.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping(value = "api/test", produces = "application/json")
    public String testApp() {
        return "API Works";
    }
}
