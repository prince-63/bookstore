package com.learn.bookstore.controllers;

import com.learn.bookstore.constants.WelcomeEndPointsConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(
        name = "Welcome Test API",
        description = "Simple test endpoint to verify if the server is running"
)
@RestController
public class WelcomeController {

    @Operation(
            summary = "Say Welcome",
            description = "Returns a static welcome message — useful for health check or test."
    )
    @GetMapping(WelcomeEndPointsConstants.WELCOME)
    public String sayWelcome() {
        return "Hello World";
    }

}
