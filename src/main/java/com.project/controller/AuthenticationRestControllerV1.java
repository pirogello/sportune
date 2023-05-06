package com.project.controller;

import com.project.config.JwtSecurityConfig;
import com.project.dto.AuthenticationRequestDto;
import com.project.dto.TokenDto;
import com.project.model.user.BaseUser;
import com.project.model.user.TestDBConnectionUser;
import com.project.repository.TestDBConnectionUserRepo;
import com.project.security.jwt.JwtTokenProvider;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Import(JwtSecurityConfig.class)
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final TestDBConnectionUserRepo repo;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, TestDBConnectionUserRepo repo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.repo = repo;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            String username = requestDto.getUsername();

            BaseUser user = userService.findByUsername(username).getBody();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            if(user == null){
                throw new UsernameNotFoundException("User with username: " + username + " not found.");
            }
            String tokenAccess = jwtTokenProvider.createAccessToken(username, Collections.singletonList(user.getRole()));
            String tokenRefresh = jwtTokenProvider.createRefreshToken(username);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("roles", Collections.singletonList(user.getRole()));
            response.put("token", tokenAccess);
            response.put("refreshtoken", tokenRefresh);

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestHeader Map<String, String> headers) {
        //String token = headers.get("authorization");
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return null;
    }

    @PostMapping("/access-token")
    public ResponseEntity updateAccessToken(@RequestBody TokenDto TokenDto){
        String refreshToken = TokenDto.getRefreshToken();
        if(refreshToken!=null && jwtTokenProvider.validateRefreshToken(refreshToken)) {
            BaseUser user = userService.findByUsername(
                    jwtTokenProvider.getUsernameFromRefreshToken(refreshToken)
            ).getBody();
            String newToken = jwtTokenProvider.createAccessToken(user.getUsername(), Collections.singletonList(user.getRole()));
            Map<Object, Object> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("token", newToken);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity("token is not valid", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/refresh-token")
    public ResponseEntity updateRefreshToken(@RequestBody TokenDto tokenDto){

        String refreshToken = tokenDto.getRefreshToken();
        if(refreshToken!=null && jwtTokenProvider.validateRefreshToken(refreshToken)) {
            BaseUser user = userService.findByUsername(
                    jwtTokenProvider.getUsernameFromRefreshToken(refreshToken)
            ).getBody();
            String newToken = jwtTokenProvider.createAccessToken(user.getUsername(), Collections.singletonList(user.getRole()));
            String newRefresh = jwtTokenProvider.createRefreshToken(user.getUsername());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("token", newToken);
            response.put("refreshtoken", newRefresh);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity("refresh token is not valid", HttpStatus.BAD_REQUEST);

    }
}
