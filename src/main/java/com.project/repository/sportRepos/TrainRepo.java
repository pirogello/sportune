package com.project.repository.sportRepos;


import com.project.model.sport.Train;
import com.project.model.user.Trainer;
import com.project.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TrainRepo extends JpaRepository<Train, UUID> {
    List<Train> findAllByUsersIn(Set Users);
    List<Train> findAllByTrainer(Trainer trainer);
}
