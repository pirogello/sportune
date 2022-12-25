package com.project.sportune.sportune.security;

import com.project.sportune.sportune.model.User;
import com.project.sportune.sportune.security.jwt.JwtUser;
import com.project.sportune.sportune.security.jwt.JwtUserFactory;
import com.project.sportune.sportune.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("In loadUserByUsername - user with username {} successfully loaded", username);
        return jwtUser;
    }
}
