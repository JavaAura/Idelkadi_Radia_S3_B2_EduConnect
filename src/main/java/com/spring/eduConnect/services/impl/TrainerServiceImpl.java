package com.spring.eduConnect.services.impl;

import com.spring.eduConnect.dto.TrainerDTO;
import com.spring.eduConnect.entities.Trainer;
import com.spring.eduConnect.repositories.TrainerRepository;
import com.spring.eduConnect.services.TrainerService;
import com.spring.eduConnect.utils.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository, TrainerMapper trainerMapper) {
        this.trainerRepository = trainerRepository;
        this.trainerMapper = trainerMapper;
    }

    @Override
    public TrainerDTO createTrainer(TrainerDTO trainerDTO) {

        Trainer trainer = trainerMapper.toEntity(trainerDTO);
        trainer = trainerRepository.save(trainer);
        return trainerMapper.toDto(trainer);
    }

    @Override
    public TrainerDTO updateTrainer(Long id, TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found with id " + id));

        Trainer updatedTrainer = trainerMapper.toEntity(trainerDTO);
        updatedTrainer.setId(trainer.getId());

        trainerRepository.save(updatedTrainer);

        return trainerMapper.toDto(updatedTrainer);
    }


    @Override
    public TrainerDTO getTrainerById(Long id) {
        Trainer Trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found with id " + id));
        return trainerMapper.toDto(Trainer);
    }

    @Override
    public List<TrainerDTO> getAllTrainers() {
        return trainerRepository.findAll().stream()
                .map(trainer -> trainerMapper.toDto(trainer))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTrainer(Long id) {
        if (!trainerRepository.existsById(id)) {
            throw new EntityNotFoundException("Trainer not found with id " + id);
        }
        trainerRepository.deleteById(id);
    }

    @Override
    public Page<TrainerDTO> getAllTrainersPaginated(Pageable pageable) {
        return trainerRepository.findAll(pageable).map(trainerEntity -> trainerMapper.toDto(trainerEntity));
    }
}
