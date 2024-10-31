package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.TrainerDTO;
import com.spring.eduConnect.services.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    private final TrainerService trainerService;
    private static final Logger logger = LoggerFactory.getLogger(TrainerController.class);

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody TrainerDTO trainerDTO) {
        logger.info("Creating trainer with name: {}", trainerDTO.getFirstName());
        TrainerDTO createdTrainer = trainerService.createTrainer(trainerDTO);
        logger.info("Trainer created with ID: {}", createdTrainer.getId());
        return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(@PathVariable Long id, @RequestBody TrainerDTO trainerDTO) {
        logger.info("Updating trainer with ID: {}", id);
        TrainerDTO updatedTrainer = trainerService.updateTrainer(id, trainerDTO);
        logger.info("Trainer updated with ID: {}", updatedTrainer.getId());
        return ResponseEntity.ok(updatedTrainer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        logger.info("Fetching trainer with ID: {}", id);
        TrainerDTO trainerDTO = trainerService.getTrainerById(id);
        logger.info("Fetched trainer: {}", trainerDTO);
        return ResponseEntity.ok(trainerDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        logger.info("Fetching all trainers");
        List<TrainerDTO> trainers = trainerService.getAllTrainers();
        logger.info("Fetched {} trainers", trainers.size());
        return ResponseEntity.ok(trainers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        logger.info("Deleting trainer with ID: {}", id);
        trainerService.deleteTrainer(id);
        logger.info("Trainer deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<TrainerDTO>> getAllTrainersPaginated(Pageable pageable) {
        logger.info("Fetching paginated trainers, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<TrainerDTO> trainers = trainerService.getAllTrainersPaginated(pageable);
        logger.info("Fetched {} trainers on page: {}", trainers.getContent().size(), pageable.getPageNumber());
        return ResponseEntity.ok(trainers);
    }
}
