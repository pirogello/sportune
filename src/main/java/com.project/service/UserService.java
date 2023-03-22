package com.project.service;

import com.project.dto.UserUpdateDto;
import com.project.errors.NoRightsToChangeError;
import com.project.model.user.BaseUser;
import com.project.repository.userRepos.BaseUserRepo;
import com.project.security.UserDetailsImpl;
import com.project.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private BaseUserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public BaseUser findById(UUID id){
       Optional<BaseUser> user = userRepo.findById(id);
        return user.orElse(null);
    }

    public BaseUser findByUsername(String username){
        log.info("In findByUsername. Username: {}", username);
        return userRepo.findByUsername(username);
    }

    public BaseUser register(BaseUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        BaseUser registeredUser = userRepo.save(user);
        log.info("In register - user {} successfuly registered", registeredUser);
        return registeredUser;
    }

    public BaseUser save(BaseUser user){
        return userRepo.save(user);
    }

    public ResponseEntity<BaseUser> updateUser(UserUpdateDto userUpDto, UUID id){
        BaseUser dBUser = userRepo.findById(userUpDto.getId()).orElse(null);
        if(dBUser == null || !dBUser.getId().equals(id)){
            throw NoRightsToChangeError.builder().message("No rights to change").status(HttpStatus.FORBIDDEN).build();
        }
        String username = userUpDto.getUsername();
        String info = userUpDto.getInfo();
        if (username != null && !username.equals("")) {
            dBUser.setUsername(username);
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            return new ResponseEntity<>(userRepo.save(dBUser), HttpStatus.UNAUTHORIZED);
        }
        if(info != null)
            dBUser.setInfo(info);

        return new ResponseEntity<>(userRepo.save(dBUser), HttpStatus.OK);
    }
}
