package com.example.demo.controller;

import com.example.demo.jwt.CustomJwt;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@CrossOrigin(
        origins = "http://Localhost:4200",
        allowedHeaders = "*",
        methods = { RequestMethod.GET }
)
public class HelloController {
    
    @PreAuthorize("hasAuthority('ROLE_fullstack-developer')")
    @GetMapping("/hello")
    public Message hello() {
        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        var  message = MessageFormat.format(
                "Hello Ngo hoang nam ", jwt.getFirstName(),jwt.getLastName()
        );
        return new Message(message);
    }


    record Message(String message) {}

}
