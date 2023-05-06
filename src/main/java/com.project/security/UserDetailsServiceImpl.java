package com.project.security;

import com.project.model.user.BaseUser;
import com.project.model.user.TestDBConnectionUser;
import com.project.repository.TestDBConnectionUserRepo;
import com.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final TestDBConnectionUserRepo repo;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, TestDBConnectionUserRepo repo) {
        this.userService = userService;
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser user = userService.findByUsername(username).getBody();
        if (user == null)
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        UserDetailsImpl userDetail = UserDetailsFactory.create(user);
        log.info("In loadUserByUsername - user with username {} successfully loaded", username);
        return userDetail;
    }
}
