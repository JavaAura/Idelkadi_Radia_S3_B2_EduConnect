package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.services.LearnerService;
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
public class LearnerController {
    private final LearnerService learnerService;
    private static final Logger logger = LoggerFactory.getLogger(LearnerController.class);

    @Autowired
    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @PostMapping
    public ResponseEntity<LearnerDTO> createLearner(@RequestBody LearnerDTO learnerDTO) {
        logger.info("Creating learner with name: {}", learnerDTO.getFirstName());
        LearnerDTO createdLearner = learnerService.createLearner(learnerDTO);
        logger.info("Learner created with ID: {}", createdLearner.getId());
        return new ResponseEntity<>(createdLearner, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearnerDTO> updateLearner(@PathVariable Long id, @RequestBody LearnerDTO learnerDTO) {
        logger.info("Updating learner with ID: {}", id);
        LearnerDTO updatedLearner = learnerService.updateLearner(id, learnerDTO);
        logger.info("Learner updated with ID: {}", updatedLearner.getId());
        return ResponseEntity.ok(updatedLearner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearnerDTO> getLearnerById(@PathVariable Long id) {
        logger.info("Fetching learner with ID: {}", id);
        LearnerDTO learnerDTO = learnerService.getLearnerById(id);
        logger.info("Fetched learner: {}", learnerDTO);
        return ResponseEntity.ok(learnerDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LearnerDTO>> getAllLearners() {
        logger.info("Fetching all learners");
        List<LearnerDTO> learners = learnerService.getAllLearners();
        logger.info("Fetched {} learners", learners.size());
        return ResponseEntity.ok(learners);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearner(@PathVariable Long id) {
        logger.info("Deleting learner with ID: {}", id);
        learnerService.deleteLearner(id);
        logger.info("Learner deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<LearnerDTO>> getAllLearnersPaginated(Pageable pageable) {
        logger.info("Fetching paginated learners, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<LearnerDTO> learners = learnerService.getAllLearnersPaginated(pageable);
        logger.info("Fetched {} learners on page: {}", learners.getContent().size(), pageable.getPageNumber());
        return ResponseEntity.ok(learners);
    }
}
