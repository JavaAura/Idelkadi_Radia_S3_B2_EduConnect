package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.TrainingDTO;
import com.spring.eduConnect.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody TrainingDTO trainingDTO) {
        TrainingDTO createdTraining = trainingService.createTraining(trainingDTO);
        return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDTO> updateTraining(
            @PathVariable Long id,
            @Valid @RequestBody TrainingDTO trainingDTO) {
        TrainingDTO updatedTraining = trainingService.updateTraining(id, trainingDTO);
        return ResponseEntity.ok(updatedTraining);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> getTrainingById(@PathVariable Long id) {
        TrainingDTO trainingDTO = trainingService.getTrainingById(id);
        return ResponseEntity.ok(trainingDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TrainingDTO>> getAllTrainings() {
        List<TrainingDTO> trainings = trainingService.getAllTrainings();
        return ResponseEntity.ok(trainings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }
}
