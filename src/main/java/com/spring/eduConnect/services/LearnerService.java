package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.entities.Learner;
import com.spring.eduConnect.repositories.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LearnerService {

    @Autowired
    private LearnerRepository learnerRepository;

    public List<LearnerDTO> getAllLearners() {
        return learnerRepository.findAll()
                .stream()
                .map(learner -> new LearnerDTO(learner.getId(), learner.getFName() , learner.getLName(), learner.getEmail(), learner.getLevel(), 1L))
                .collect(Collectors.toList());
    }
}

