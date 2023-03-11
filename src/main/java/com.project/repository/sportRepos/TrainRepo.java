package com.project.repository.sportRepos;


import com.project.model.sport.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainRepo extends JpaRepository<Train, UUID> {
}
