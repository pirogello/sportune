package com.project.sportune.sportune.controller;


import com.project.sportune.sportune.model.User;

import com.project.sportune.sportune.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("{id}")
    public Optional<User> profile(@PathVariable String id){
        System.out.println(userRepo.findById(UUID.fromString(id)));
        return userRepo.findById(UUID.fromString(id));
    }
}
