package com.project.controller;

import com.project.model.user.TestDBConnectionUser;
import com.project.repository.TestDBConnectionUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

@RequestMapping(value = "/test/api")
public class TestController {

    @Autowired
    private TestDBConnectionUserRepo repo;


    @GetMapping("/get/{id}")
    public Optional<TestDBConnectionUser> getUser(@PathVariable Integer id){
        return repo.findById(id);
    }
}
