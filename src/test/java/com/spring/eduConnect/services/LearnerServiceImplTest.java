package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.entities.Learner;
import com.spring.eduConnect.repositories.LearnerRepository;
import com.spring.eduConnect.services.impl.LearnerServiceImpl;
import com.spring.eduConnect.utils.LearnerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class LearnerServiceImplTest {

    @Mock
    private LearnerRepository learnerRepository;

    @Mock
    private LearnerMapper learnerMapper;

    @InjectMocks
    private LearnerServiceImpl learnerService;

    private LearnerDTO learnerDTO;
    private Learner learnerEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        learnerDTO = new LearnerDTO();
        learnerDTO.setFirstName("John Doe");
        learnerDTO.setEmail("john.doe@example.com");

        learnerEntity = new Learner();
        learnerEntity.setId(1L);
        learnerEntity.setFirstName("John Doe");
        learnerEntity.setEmail("john.doe@example.com");
    }

    @Test
    void testCreateLearner() {
        // Arrange
        when(learnerMapper.toEntity(learnerDTO)).thenReturn(learnerEntity);
        when(learnerRepository.save(learnerEntity)).thenReturn(learnerEntity);
        when(learnerMapper.toDto(learnerEntity)).thenReturn(learnerDTO);

        // Act
        LearnerDTO result = learnerService.createLearner(learnerDTO);

        // Assert
        assertNotNull(result);
        assertEquals(learnerDTO.getFirstName(), result.getFirstName());
        assertEquals(learnerDTO.getEmail(), result.getEmail());

        verify(learnerMapper, times(1)).toEntity(learnerDTO);
        verify(learnerRepository, times(1)).save(learnerEntity);
        verify(learnerMapper, times(1)).toDto(learnerEntity);
    }
}
