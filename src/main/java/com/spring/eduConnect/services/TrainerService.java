package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {
    TrainerDTO createTrainer(TrainerDTO TrainerDTO);
    TrainerDTO updateTrainer(Long id, TrainerDTO TrainerDTO);
    TrainerDTO getTrainerById(Long id);
    List<TrainerDTO> getAllTrainers();
    void deleteTrainer(Long id);
}
