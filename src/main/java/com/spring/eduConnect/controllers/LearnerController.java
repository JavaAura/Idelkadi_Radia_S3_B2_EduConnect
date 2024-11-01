package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.services.LearnerService;
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
@RequestMapping("/api/learner")
@Tag(name = "Learner Management", description = "Operations related to learners")
public class LearnerController {

    private final LearnerService learnerService;
    private static final Logger logger = LoggerFactory.getLogger(LearnerController.class);

    @Autowired
    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @Operation(summary = "Create a new learner", description = "Creates a new learner in the system.")
    @ApiResponse(responseCode = "201", description = "Learner created successfully")
    @PostMapping
    public ResponseEntity<LearnerDTO> createLearner(@RequestBody LearnerDTO learnerDTO) {
        logger.info("Creating learner with name: {}", learnerDTO.getFirstName());
        LearnerDTO createdLearner = learnerService.createLearner(learnerDTO);
        logger.info("Learner created with ID: {}", createdLearner.getId());
        return new ResponseEntity<>(createdLearner, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing learner", description = "Updates an existing learner based on ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Learner updated successfully"),
            @ApiResponse(responseCode = "404", description = "Learner not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LearnerDTO> updateLearner(
            @PathVariable @Parameter(description = "ID of the learner to be updated") Long id,
            @RequestBody LearnerDTO learnerDTO) {
        logger.info("Updating learner with ID: {}", id);
        LearnerDTO updatedLearner = learnerService.updateLearner(id, learnerDTO);
        logger.info("Learner updated with ID: {}", updatedLearner.getId());
        return ResponseEntity.ok(updatedLearner);
    }

    @Operation(summary = "Get learner by ID", description = "Fetches a learner based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Learner fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Learner not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LearnerDTO> getLearnerById(
            @PathVariable @Parameter(description = "ID of the learner to retrieve") Long id) {
        logger.info("Fetching learner with ID: {}", id);
        LearnerDTO learnerDTO = learnerService.getLearnerById(id);
        logger.info("Fetched learner: {}", learnerDTO);
        return ResponseEntity.ok(learnerDTO);
    }

    @Operation(summary = "Get all learners", description = "Fetches all learners from the system.")
    @GetMapping("/all")
    public ResponseEntity<List<LearnerDTO>> getAllLearners() {
        logger.info("Fetching all learners");
        List<LearnerDTO> learners = learnerService.getAllLearners();
        logger.info("Fetched {} learners", learners.size());
        return ResponseEntity.ok(learners);
    }

    @Operation(summary = "Delete a learner by ID", description = "Deletes a learner based on its ID.")
    @ApiResponse(responseCode = "204", description = "Learner deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearner(@PathVariable @Parameter(description = "ID of the learner to delete") Long id) {
        logger.info("Deleting learner with ID: {}", id);
        learnerService.deleteLearner(id);
        logger.info("Learner deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all learners paginated", description = "Fetches all learners in a paginated format.")
    @GetMapping("/paginated")
    public ResponseEntity<Page<LearnerDTO>> getAllLearnersPaginated(Pageable pageable) {
        logger.info("Fetching paginated learners, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<LearnerDTO> learners = learnerService.getAllLearnersPaginated(pageable);
        logger.info("Fetched {} learners on page: {}", learners.getContent().size());
        return ResponseEntity.ok(learners);
    }
}
