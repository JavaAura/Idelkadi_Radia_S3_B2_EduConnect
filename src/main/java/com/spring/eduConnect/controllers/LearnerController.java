package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.services.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LearnerController {
    @Autowired
    private LearnerService learnerService;

    @GetMapping("/api/learners")
    public List<LearnerDTO> getAllLearners() {
        return learnerService.getAllLearners();
    }
}
