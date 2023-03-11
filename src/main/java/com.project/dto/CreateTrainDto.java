package com.project.dto;

import com.project.model.user.Trainer;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CreateTrainDto {
    private String title;
    protected LocalDateTime startTrain;
    protected LocalDateTime endTrain;
    private String trainerUsername;
}
