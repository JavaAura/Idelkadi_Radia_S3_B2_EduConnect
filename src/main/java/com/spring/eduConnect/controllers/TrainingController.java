package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.TrainingDTO;
import com.spring.eduConnect.entities.enums.TrainingStatus;
import com.spring.eduConnect.services.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/training")
@Tag(name = "Training Management", description = "Operations related to trainings")
public class TrainingController {

    private final TrainingService trainingService;
    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Operation(summary = "Create a new training", description = "Creates a new training in the system.")
    @ApiResponse(responseCode = "201", description = "Training created successfully")
    @PostMapping
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody @Valid TrainingDTO trainingDTO) {
        logger.info("Creating training with title: {}", trainingDTO.getTitle());
        TrainingDTO createdTraining = trainingService.createTraining(trainingDTO);
        logger.info("Training created with ID: {}", createdTraining.getId());
        return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing training", description = "Updates an existing training based on ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training updated successfully"),
            @ApiResponse(responseCode = "404", description = "Training not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TrainingDTO> updateTraining(
            @PathVariable @Parameter(description = "ID of the training to be updated") Long id,
            @Valid @RequestBody TrainingDTO trainingDTO) {
        logger.info("Updating training with ID: {}", id);
        TrainingDTO updatedTraining = trainingService.updateTraining(id, trainingDTO);
        logger.info("Training updated with ID: {}", updatedTraining.getId());
        return ResponseEntity.ok(updatedTraining);
    }

    @Operation(summary = "Get training by ID", description = "Fetches a training based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Training not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> getTrainingById(
            @PathVariable @Parameter(description = "ID of the training to retrieve") Long id) {
        logger.info("Fetching training with ID: {}", id);
        TrainingDTO trainingDTO = trainingService.getTrainingById(id);
        logger.info("Fetched training: {}", trainingDTO);
        return ResponseEntity.ok(trainingDTO);
    }

    @Operation(summary = "Get all trainings", description = "Fetches all trainings from the system.")
    @GetMapping("/all")
    public ResponseEntity<List<TrainingDTO>> getAllTrainings() {
        logger.info("Fetching all trainings");
        List<TrainingDTO> trainings = trainingService.getAllTrainings();
        logger.info("Fetched {} trainings", trainings.size());
        return ResponseEntity.ok(trainings);
    }

    @Operation(summary = "Delete a training by ID", description = "Deletes a training based on its ID.")
    @ApiResponse(responseCode = "204", description = "Training deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(
            @PathVariable @Parameter(description = "ID of the training to delete") Long id) {
        logger.info("Deleting training with ID: {}", id);
        trainingService.deleteTraining(id);
        logger.info("Training deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search trainings by title", description = "Searches trainings by their title.")
    @GetMapping("/search/title")
    public ResponseEntity<List<TrainingDTO>> getTrainingsByTitle(@RequestParam String title) {
        logger.info("Searching trainings with title: {}", title);
        List<TrainingDTO> trainings = trainingService.getTrainingsByTitle(title);
        logger.info("Found {} trainings with title: {}", trainings.size(), title);
        return ResponseEntity.ok(trainings);
    }

    @Operation(summary = "Search trainings by level and status", description = "Searches trainings by their level and status.")
    @GetMapping("/search/level-status")
    public ResponseEntity<List<TrainingDTO>> getTrainingsByLevelAndStatus(
            @RequestParam String level,
            @RequestParam TrainingStatus status) {
        logger.info("Searching trainings with level: {} and status: {}", level, status);
        List<TrainingDTO> trainings = trainingService.getTrainingsByLevelAndStatus(level, status);
        logger.info("Found {} trainings with level: {} and status: {}", trainings.size(), level, status);
        return ResponseEntity.ok(trainings);
    }

    @Operation(summary = "Search trainings within a date range", description = "Fetches trainings within a specified date range.")
    @GetMapping("/search/date-range")
    public ResponseEntity<List<TrainingDTO>> getTrainingsWithinDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        logger.info("Searching trainings within date range: {} to {}", startDate, endDate);
        List<TrainingDTO> trainings = trainingService.getTrainingsWithinDateRange(startDate, endDate);
        logger.info("Found {} trainings within date range: {} to {}", trainings.size(), startDate, endDate);
        return ResponseEntity.ok(trainings);
    }

    @Operation(summary = "Get all trainings paginated", description = "Fetches all trainings in a paginated format.")
    @GetMapping("/paginated")
    public ResponseEntity<Page<TrainingDTO>> getAllTrainingsPaginated(Pageable pageable) {
        logger.info("Fetching paginated trainings, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<TrainingDTO> trainings = trainingService.getAllTrainingsPaginated(pageable);
        logger.info("Fetched {} trainings on page: {}", trainings.getContent().size(), pageable.getPageNumber());
        return ResponseEntity.ok(trainings);
    }
}