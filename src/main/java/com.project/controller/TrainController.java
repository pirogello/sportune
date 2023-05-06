package com.project.controller;

import com.project.dto.CreateTrainDto;
import com.project.dto.UserUpdateDto;
import com.project.model.sport.Train;
import com.project.service.TrainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/train")
@Slf4j
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/{trainId}/changeUser/{username}")
    public ResponseEntity<Train> addUser(@PathVariable UUID trainId, @PathVariable String username){
        return trainService.addUserToTrain(trainId, username);
    }

    @PostMapping("/create")
    public Train create( @RequestBody CreateTrainDto train){
        return trainService.createTrain(train);
    }
}
