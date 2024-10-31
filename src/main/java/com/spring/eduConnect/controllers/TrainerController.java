package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.TrainerDTO;
import com.spring.eduConnect.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<TrainerDTO> createTraining( @RequestBody TrainerDTO trainerDTO) {
        TrainerDTO createdTrainer = trainerService.createTrainer(trainerDTO);
        return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTraining(
            @PathVariable Long id,
            @RequestBody TrainerDTO trainerDTO) {
        TrainerDTO updatedTrainer = trainerService.updateTrainer(id, trainerDTO);
        return ResponseEntity.ok(updatedTrainer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainingById(@PathVariable Long id) {
        TrainerDTO trainerDTO = trainerService.getTrainerById(id);
        return ResponseEntity.ok(trainerDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        List<TrainerDTO> trainers = trainerService.getAllTrainers();
        return ResponseEntity.ok(trainers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
