package com.project.service;

import com.project.model.user.BaseUser;
import com.project.repository.userRepos.BaseUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private BaseUserRepo userRepo;

    public Optional<BaseUser> getUserById(UUID id){

        return userRepo.findById(id);
    }
}
