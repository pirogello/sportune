package com.project.controller;

import com.project.dto.UserUpdateDto;
import com.project.model.user.BaseUser;
import com.project.security.UserDetailsImpl;
import com.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/findUser")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{user}")
    public BaseUser getUser(@PathVariable BaseUser user){
        return user;
    }

    @GetMapping("/find/{username}")
    public BaseUser getByUsername(@PathVariable String username){
        System.out.println(username);
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //System.out.println(userDetails);
        log.info("In find user");
        return userService.findByUsername(username);
    }

    @PostMapping("/update")
    public ResponseEntity<BaseUser> update(@RequestBody UserUpdateDto userUpDto, @AuthenticationPrincipal UserDetailsImpl usr){
        log.info("In update user");
        return userService.updateUser(userUpDto, usr.getId());
    }
}
