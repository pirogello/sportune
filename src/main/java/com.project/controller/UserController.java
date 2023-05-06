package com.project.controller;

import com.project.dto.UserUpdateDto;
import com.project.errors.NoDataError;
import com.project.model.Role;
import com.project.model.sport.Train;
import com.project.model.user.BaseUser;
import com.project.model.user.Trainer;
import com.project.model.user.User;
import com.project.repository.sportRepos.TrainRepo;
import com.project.security.UserDetailsImpl;
import com.project.service.TrainService;
import com.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/findUser")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrainService trainService;

    @GetMapping("{user}")
    public BaseUser getUser(@PathVariable BaseUser user){
        return user;
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<BaseUser> getByUsername(@PathVariable String username){
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

    @GetMapping("{username}/trains")
    public ResponseEntity<List<Train>> getTrains(@PathVariable String username){

        BaseUser user1 = userService.findByUsername(username).getBody();
        if(user1 == null)
            throw new NoDataError("Нет данных",HttpStatus.NOT_FOUND);
        if (user1.getRole().equals(Role.USER)) {
            return trainService.getTrainsByUsers(Set.of((User)user1));
        }
        else if(user1.getRole().equals(Role.TRAINER)){
            return trainService.getTrainsByTrainer((Trainer) user1);
        }
        return null;
    }
}
