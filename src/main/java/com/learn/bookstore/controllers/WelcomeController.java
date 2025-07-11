package com.learn.bookstore.controllers;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class WelcomeController {

    @GetMapping("/say-welcome")
    public String sayWelcome() {
        return "Hello World";
    }

    @PostMapping("/jwt")
    public String jwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BadCredentialsException("Invalid Token received!");
        }
        return authentication.getName();
    }
}
