package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.services.LearnerService;
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

    @Autowired
    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @PostMapping
    public ResponseEntity<LearnerDTO> createLearner(@RequestBody LearnerDTO learnerDTO) {
        LearnerDTO createdLearner = learnerService.createLearner(learnerDTO);
        return new ResponseEntity<>(createdLearner, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearnerDTO> updateLearner(@PathVariable Long id, @RequestBody LearnerDTO learnerDTO) {
        LearnerDTO updatedLearner = learnerService.updateLearner(id, learnerDTO);
        return ResponseEntity.ok(updatedLearner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearnerDTO> getLearnerById(@PathVariable Long id) {
        LearnerDTO learnerDTO = learnerService.getLearnerById(id);
        return ResponseEntity.ok(learnerDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LearnerDTO>> getAllLearners() {
        List<LearnerDTO> learners = learnerService.getAllLearners();
        return ResponseEntity.ok(learners);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearner(@PathVariable Long id) {
        learnerService.deleteLearner(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<LearnerDTO>> getAllLearnersPaginated(Pageable pageable) {
        Page<LearnerDTO> learners = learnerService.getAllLearnersPaginated(pageable);
        return ResponseEntity.ok(learners);
    }
}
