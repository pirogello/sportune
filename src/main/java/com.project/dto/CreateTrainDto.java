package com.project.dto;

import com.project.model.user.Trainer;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CreateTrainDto {
    protected LocalDateTime startTrain;
    protected LocalDateTime endTrain;
    protected String location;
    protected String trainerUsername;

}
