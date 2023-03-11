package com.project.service;

import com.project.dto.CreateTrainDto;
import com.project.model.sport.Train;
import com.project.model.user.Trainer;
import com.project.model.user.User;
import com.project.repository.sportRepos.TrainRepo;
import com.project.repository.userRepos.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainService {

    @Autowired
    private TrainRepo trainRepo;
    @Autowired
    private TrainerRepo trainerRepo;

    public Train createTrain(CreateTrainDto trainDto){
        Train train = new Train(trainDto.getTitle(), trainDto.getStartTrain(), trainDto.getEndTrain());
        train.addTrainer(trainerRepo.findByUsername(trainDto.getTrainerUsername()));
        return trainRepo.save(train);
    }

    public Train addUser(Train train, User user){
        train.addUser(user);
        return trainRepo.save(train);
    }
}
