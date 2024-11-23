package com.authenticate.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping("/getStatus")
    public String getStatus() {
        String htmlString = "<!DOCTYPE html>"
                            +"<html>"
                            +"<head></head>"
                            +"<body>"
                            +"<center>"
                            +"<h1>Status Up</h1>"
                            +"</center>"
                            +"</body>"
                            +"</html>";
        return htmlString;
    }
    

}
