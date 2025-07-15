package com.learn.bookstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class WelcomeController {

    @GetMapping("/say-welcome")
    public String sayWelcome() {
        return "Hello World";
    }

}
