package com.spring.eduConnect.utils;

import com.spring.eduConnect.dto.TrainingDTO;
import com.spring.eduConnect.entities.Training;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingDTO toDto(Training training) {
        if (training == null) {
            return null;
        }

        TrainingDTO dto = new TrainingDTO();
        dto.setId(training.getId());
        dto.setTitle(training.getTitle());
        dto.setLevel(training.getLevel());
        dto.setPrerequisites(training.getPrerequisites());
        dto.setMinCapacity(training.getMinimumCapacity());
        dto.setMaxCapacity(training.getMaximumCapacity());
        dto.setStartDate(training.getStartDate());
        dto.setEndDate(training.getEndDate());
        dto.setStatus(training.getStatus());
        return dto;
    }

    public Training toEntity(TrainingDTO trainingDTO) {
        if (trainingDTO == null) {
            return null;
        }

        Training training = new Training();
        training.setId(trainingDTO.getId());
        training.setTitle(trainingDTO.getTitle());
        training.setLevel(trainingDTO.getLevel());
        training.setPrerequisites(trainingDTO.getPrerequisites());
        training.setMinimumCapacity(trainingDTO.getMinCapacity());
        training.setMaximumCapacity(trainingDTO.getMaxCapacity());
        training.setStartDate(trainingDTO.getStartDate());
        training.setEndDate(trainingDTO.getEndDate());
        training.setStatus(trainingDTO.getStatus());
        return training;
    }
}
