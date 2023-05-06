package com.project.service;

import com.project.dto.CreateTrainDto;
import com.project.model.sport.Train;
import com.project.model.user.Trainer;
import com.project.model.user.User;
import com.project.repository.sportRepos.TrainRepo;
import com.project.repository.userRepos.TrainerRepo;
import com.project.repository.userRepos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class TrainService {

    @Autowired
    private TrainRepo trainRepo;
    @Autowired
    private TrainerRepo trainerRepo;
    @Autowired
    private UserRepo userRepo;

    public Train createTrain(CreateTrainDto trainDto) {
        Train train = new Train(trainDto.getLocation(), trainDto.getStartTrain(), trainDto.getStartTrain());
        train.addTrainer(trainerRepo.findByUsername(trainDto.getTrainerUsername()));
        return trainRepo.save(train);
    }

    public Train addUser(Train train, User user) {
        train.addUser(user);
        return trainRepo.save(train);
    }

    public ResponseEntity<List<Train>> getTrainsByUsers(Set<User> users) {
        List<Train> trains = trainRepo.findAllByUsersIn(users);
        if (trains == null) {
            System.out.println("ERROR");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }


    public ResponseEntity<List<Train>> getTrainsByTrainer(Trainer trainer) {
        List<Train> trains = trainRepo.findAllByTrainer(trainer);
        if (trains == null) {
            System.out.println("ERROR");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    public ResponseEntity<Train> addUserToTrain(UUID trainId, String username) {

        Train train = trainRepo.getById(trainId);
        User user = userRepo.findByUsername(username);
        if (train.getUsers().contains(user)) {
            train.deleteUser(user);
        }
        else {
            train.addUser(user);
        }
        return new ResponseEntity<>(trainRepo.save(train), HttpStatus.OK);
    }
}
