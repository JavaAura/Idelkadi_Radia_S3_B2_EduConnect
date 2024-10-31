package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.TrainerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrainerService {
    TrainerDTO createTrainer(TrainerDTO TrainerDTO);
    TrainerDTO updateTrainer(Long id, TrainerDTO TrainerDTO);
    TrainerDTO getTrainerById(Long id);
    List<TrainerDTO> getAllTrainers();
    void deleteTrainer(Long id);
    Page<TrainerDTO> getAllTrainersPaginated(Pageable pageable);
}
