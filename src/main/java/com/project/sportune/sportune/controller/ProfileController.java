package com.project.sportune.sportune.controller;


import com.project.sportune.sportune.model.User;

import com.project.sportune.sportune.repository.UserRepo;
import com.project.sportune.sportune.security.jwt.JwtTokenProvider;
import com.project.sportune.sportune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider jwtTokenProvider;
//    private final UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("{id}")
    public Optional<User> profile(@PathVariable String id){
        System.out.println(userRepo.findById(UUID.fromString(id)));
        return userRepo.findById(UUID.fromString(id));
    }
    @GetMapping("coach")
    public String profile(){
        System.out.println("check");
        return "check";
    }
    @GetMapping("test")
    public String test(){
        System.out.println("test");
        return "test";
    }
}
