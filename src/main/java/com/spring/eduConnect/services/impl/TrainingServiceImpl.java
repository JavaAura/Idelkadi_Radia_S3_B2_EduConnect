package com.spring.eduConnect.services.impl;

import com.spring.eduConnect.dto.TrainingDTO;
import com.spring.eduConnect.entities.Training;
import com.spring.eduConnect.repositories.TrainingRepository;
import com.spring.eduConnect.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import javax.persistence.EntityNotFoundException;
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
        Training training = modelMapper.map(trainingDTO, Training.class);
        training = trainingRepository.save(training);
        return modelMapper.map(training, TrainingDTO.class);
    }

    @Override
    public TrainingDTO updateTraining(Long id, TrainingDTO trainingDTO) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training not found with id " + id));

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
}
