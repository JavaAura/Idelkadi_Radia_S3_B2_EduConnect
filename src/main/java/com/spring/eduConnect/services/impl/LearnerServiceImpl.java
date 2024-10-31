package com.spring.eduConnect.services.impl;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.entities.Learner;
import com.spring.eduConnect.repositories.LearnerRepository;
import com.spring.eduConnect.services.LearnerService;
import com.spring.eduConnect.utils.LearnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearnerServiceImpl implements LearnerService {

    private final LearnerRepository learnerRepository;
    private final LearnerMapper learnerMapper;

    @Autowired
    public LearnerServiceImpl(LearnerRepository learnerRepository, LearnerMapper learnerMapper) {
        this.learnerRepository = learnerRepository;
        this.learnerMapper = learnerMapper;
    }

    @Override
    public LearnerDTO createLearner(LearnerDTO learnerDTO) {
        Learner learner = learnerMapper.toEntity(learnerDTO);
        learner = learnerRepository.save(learner);
        return learnerMapper.toDto(learner);
    }

    @Override
    public LearnerDTO updateLearner(Long id, LearnerDTO learnerDTO) {
        Learner learner = learnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Learner not found with id " + id));

        Learner updatedLearner = learnerMapper.toEntity(learnerDTO);
        updatedLearner.setId(learner.getId());

        learnerRepository.save(updatedLearner);

        return learnerMapper.toDto(updatedLearner);
    }

    @Override
    public LearnerDTO getLearnerById(Long id) {
        Learner learner = learnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Learner not found with id " + id));
        return learnerMapper.toDto(learner);
    }

    @Override
    public List<LearnerDTO> getAllLearners() {
        return learnerRepository.findAll().stream()
                .map(learnerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLearner(Long id) {
        if (!learnerRepository.existsById(id)) {
            throw new EntityNotFoundException("Learner not found with id " + id);
        }
        learnerRepository.deleteById(id);
    }

    @Override
    public Page<LearnerDTO> getAllLearnersPaginated(Pageable pageable) {
        return learnerRepository.findAll(pageable).map(learnerEntity -> learnerMapper.toDto(learnerEntity));
    }
}
