package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.TrainerDTO;
import com.spring.eduConnect.services.TrainerService;
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

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
@Tag(name = "Trainer Management", description = "Operations related to trainers")
public class TrainerController {

    private final TrainerService trainerService;
    private static final Logger logger = LoggerFactory.getLogger(TrainerController.class);

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Operation(summary = "Create a new trainer", description = "Creates a new trainer in the system.")
    @ApiResponse(responseCode = "201", description = "Trainer created successfully")
    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody TrainerDTO trainerDTO) {
        logger.info("Creating trainer with name: {}", trainerDTO.getFirstName());
        TrainerDTO createdTrainer = trainerService.createTrainer(trainerDTO);
        logger.info("Trainer created with ID: {}", createdTrainer.getId());
        return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing trainer", description = "Updates an existing trainer based on ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Trainer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(
            @PathVariable @Parameter(description = "ID of the trainer to be updated") Long id,
            @RequestBody TrainerDTO trainerDTO) {
        logger.info("Updating trainer with ID: {}", id);
        TrainerDTO updatedTrainer = trainerService.updateTrainer(id, trainerDTO);
        logger.info("Trainer updated with ID: {}", updatedTrainer.getId());
        return ResponseEntity.ok(updatedTrainer);
    }

    @Operation(summary = "Get trainer by ID", description = "Fetches a trainer based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainer fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Trainer not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(
            @PathVariable @Parameter(description = "ID of the trainer to retrieve") Long id) {
        logger.info("Fetching trainer with ID: {}", id);
        TrainerDTO trainerDTO = trainerService.getTrainerById(id);
        logger.info("Fetched trainer: {}", trainerDTO);
        return ResponseEntity.ok(trainerDTO);
    }

    @Operation(summary = "Get all trainers", description = "Fetches all trainers from the system.")
    @GetMapping("/all")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        logger.info("Fetching all trainers");
        List<TrainerDTO> trainers = trainerService.getAllTrainers();
        logger.info("Fetched {} trainers", trainers.size());
        return ResponseEntity.ok(trainers);
    }

    @Operation(summary = "Delete a trainer by ID", description = "Deletes a trainer based on its ID.")
    @ApiResponse(responseCode = "204", description = "Trainer deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable @Parameter(description = "ID of the trainer to delete") Long id) {
        logger.info("Deleting trainer with ID: {}", id);
        trainerService.deleteTrainer(id);
        logger.info("Trainer deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all trainers paginated", description = "Fetches all trainers in a paginated format.")
    @GetMapping("/paginated")
    public ResponseEntity<Page<TrainerDTO>> getAllTrainersPaginated(Pageable pageable) {
        logger.info("Fetching paginated trainers, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<TrainerDTO> trainers = trainerService.getAllTrainersPaginated(pageable);
        logger.info("Fetched {} trainers on page: {}", trainers.getContent().size());
        return ResponseEntity.ok(trainers);
    }
}
