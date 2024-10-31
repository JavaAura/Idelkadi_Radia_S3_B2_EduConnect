package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.entities.Learner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface LearnerService {

    LearnerDTO createLearner(LearnerDTO learnerDTO);

    LearnerDTO updateLearner(Long id, LearnerDTO learnerDTO);

    LearnerDTO getLearnerById(Long id);

    List<LearnerDTO> getAllLearners();
    void deleteLearner(Long id);
    Page<LearnerDTO> getAllLearnersPaginated(Pageable pageable);
}

