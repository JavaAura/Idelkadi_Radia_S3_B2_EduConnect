package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.TrainingDTO;

import java.util.List;

public interface TrainingService {
    TrainingDTO createTraining(TrainingDTO trainingDTO);
    TrainingDTO updateTraining(Long id, TrainingDTO trainingDTO);
    TrainingDTO getTrainingById(Long id);
    List<TrainingDTO> getAllTrainings();
    void deleteTraining(Long id);
}
