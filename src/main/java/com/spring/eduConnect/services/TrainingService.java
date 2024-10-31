package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.TrainingDTO;
import com.spring.eduConnect.entities.enums.TrainingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {
    TrainingDTO createTraining(TrainingDTO trainingDTO);
    TrainingDTO updateTraining(Long id, TrainingDTO trainingDTO);
    TrainingDTO getTrainingById(Long id);
    List<TrainingDTO> getAllTrainings();
    void deleteTraining(Long id);
    List<TrainingDTO> getTrainingsByTitle(String title);
    List<TrainingDTO> getTrainingsByLevelAndStatus(String level, TrainingStatus status);
    List<TrainingDTO> getTrainingsWithinDateRange(LocalDate startDate, LocalDate endDate);
    Page<TrainingDTO> getAllTrainingsPaginated(Pageable pageable);
}
