package com.project.controller;


import com.project.model.user.BaseUser;
import com.project.repository.userRepos.UserRepo;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider jwtTokenProvider;
//    private final UserService userService;

    @Autowired
    private UserService userRepo;

    @GetMapping("{id}")
    public BaseUser profile(@PathVariable String id){
        System.out.println(userRepo.findById(UUID.fromString(id)));
        return userRepo.findById(UUID.fromString(id));
    }
    @GetMapping("admin")
    public String profile(){
        System.out.println("admin");
        return "in admin page";
    }
    @GetMapping("haveauth")
    public String test(){
        System.out.println("haveauth");
        return "in haveauth page";
    }
}
