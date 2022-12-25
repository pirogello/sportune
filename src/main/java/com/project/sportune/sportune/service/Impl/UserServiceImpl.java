package com.project.sportune.sportune.service.Impl;

import com.project.sportune.sportune.model.User;
import com.project.sportune.sportune.repository.UserRepo;
import com.project.sportune.sportune.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepo.save(user);
        log.info("In register - user {} successfuly registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> res = userRepo.findAll();
        log.info("In getAll - {} users found", res.size());
        return res;
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> ret = userRepo.findByEmail(email);
        if (ret.isPresent()){
            log.info("In findByEmail - user {} found by email {}", ret.get(), email);
            return ret.get();
        }
        log.info("In findByEmail - user not found by email: {}",  email);
        return null;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> ret = userRepo.findByUsername(username);
        if (ret.isPresent()){
            log.info("In findByUsername - user {} found by username {}", ret.get(), username);
            return ret.get();
        }
        log.info("In findByUsername - user not found by username: {}",  username);
        return null;
    }

    @Override
    public User findById(UUID id) {
        Optional<User> ret = userRepo.findById(id);
        if (ret.isPresent()){
            log.info("In findById - user {} found by id {}", ret.get(), id);
            return ret.get();
        }
        log.info("In findById - user not found by id: {}",  id);
       return null;
    }

    @Override
    public void delete(UUID id) {
        userRepo.deleteById(id);
        log.info("In delete - user with id: {} deleted",  id);
    }
}
