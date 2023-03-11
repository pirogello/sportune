package com.project.controller;

import com.project.dto.CreateTrainDto;
import com.project.model.sport.Train;
import com.project.model.sport.competition.BaseCompetition;
import com.project.model.sport.result.BaseSportResult;
import com.project.model.sport.type.BaseSport;
import com.project.model.user.User;
import com.project.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sport")
public class SportController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/{sport}")
    public BaseSport getSportById(@PathVariable BaseSport sport){
        return sport;
    }

    @GetMapping("/competition/{competition}")
    public BaseCompetition getCompetitionById(@PathVariable BaseCompetition competition){
        return competition;
    }


    @GetMapping("/result/{result}")
    public BaseSportResult getSportById(@PathVariable BaseSportResult result){
        return result;
    }


    @PostMapping("/createTrain")
    public Train createTrain(@RequestBody CreateTrainDto train){
        return trainService.createTrain(train);
    }

    @GetMapping("/train/{train}/{user}")
    public Train signUpForTrain(@PathVariable Train train, @PathVariable User user){
        return trainService.addUser(train, user);
    }
}
