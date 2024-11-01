package com.spring.eduConnect.services.impl;

import com.spring.eduConnect.dto.TrainingDTO;
import com.spring.eduConnect.entities.Training;
import com.spring.eduConnect.entities.enums.TrainingStatus;
import com.spring.eduConnect.exceptions.DataAlreadyExistsException;
import com.spring.eduConnect.repositories.TrainingRepository;
import com.spring.eduConnect.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, ModelMapper modelMapper) {
        this.trainingRepository = trainingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainingDTO createTraining(TrainingDTO trainingDTO) {
        if (trainingRepository.existsByTitle(trainingDTO.getTitle())) {
            throw new DataAlreadyExistsException("Training with title '" + trainingDTO.getTitle() + "' already exists.");
        }

        Training training = modelMapper.map(trainingDTO, Training.class);
        training = trainingRepository.save(training);
        return modelMapper.map(training, TrainingDTO.class);
    }

    @Override
    public TrainingDTO updateTraining(Long id, TrainingDTO trainingDTO) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training not found with id " + id));

        if (trainingRepository.existsByTitleAndIdNot(trainingDTO.getTitle(), id)) {
            throw new DataAlreadyExistsException("Training with title '" + trainingDTO.getTitle() + "' already exists.");
        }

        modelMapper.map(trainingDTO, training);
        training = trainingRepository.save(training);
        return modelMapper.map(training, TrainingDTO.class);
    }

    @Override
    public TrainingDTO getTrainingById(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training not found with id " + id));
        return modelMapper.map(training, TrainingDTO.class);
    }

    @Override
    public List<TrainingDTO> getAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(training -> modelMapper.map(training, TrainingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTraining(Long id) {
        if (!trainingRepository.existsById(id)) {
            throw new EntityNotFoundException("Training not found with id " + id);
        }
        trainingRepository.deleteById(id);
    }

    @Override
    public List<TrainingDTO> getTrainingsByTitle(String title) {
        return trainingRepository.findByTitle(title).stream()
                .map(training -> modelMapper.map(training, TrainingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> getTrainingsByLevelAndStatus(String level, TrainingStatus status) {
        return trainingRepository.findByLevelAndStatus(level, status).stream()
                .map(training -> modelMapper.map(training, TrainingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> getTrainingsWithinDateRange(LocalDate startDate, LocalDate endDate) {
        return trainingRepository.findTrainingsWithinDateRange(startDate, endDate).stream()
                .map(training -> modelMapper.map(training, TrainingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<TrainingDTO> getAllTrainingsPaginated(Pageable pageable) {
        return trainingRepository.findAll(pageable)
                .map(training -> modelMapper.map(training, TrainingDTO.class));
    }
}
