package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.TrainerDTO;
import com.spring.eduConnect.entities.Trainer;
import com.spring.eduConnect.repositories.TrainerRepository;
import com.spring.eduConnect.services.impl.TrainerServiceImpl;
import com.spring.eduConnect.utils.TrainerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainerMapper trainerMapper;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    private TrainerDTO trainerDTO;
    private Trainer trainerEntity;
    private Long trainerId = 1L;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trainerDTO = new TrainerDTO();
        trainerDTO.setLastName("Jane Doe");
        trainerDTO.setEmail("jane.doe@example.com");

        trainerEntity = new Trainer();
        trainerEntity.setId(1L);
        trainerEntity.setFirstName("Jane Doe");
        trainerEntity.setEmail("jane.doe@example.com");
    }

    @Test
    void testCreateTrainer() {
        // Arrange
        when(trainerMapper.toEntity(trainerDTO)).thenReturn(trainerEntity);
        when(trainerRepository.save(trainerEntity)).thenReturn(trainerEntity);
        when(trainerMapper.toDto(trainerEntity)).thenReturn(trainerDTO);

        // Act
        TrainerDTO result = trainerService.createTrainer(trainerDTO);

        // Assert
        assertNotNull(result);
        assertEquals(trainerDTO.getFirstName(), result.getFirstName());
        assertEquals(trainerDTO.getEmail(), result.getEmail());

        verify(trainerMapper, times(1)).toEntity(trainerDTO);
        verify(trainerRepository, times(1)).save(trainerEntity);
        verify(trainerMapper, times(1)).toDto(trainerEntity);
    }

    @Test
    void testUpdateTrainer_Success() {
        // Arrange
        when(trainerRepository.findById(trainerId)).thenReturn(java.util.Optional.of(trainerEntity));
        when(trainerMapper.toEntity(trainerDTO)).thenReturn(trainerEntity);
        when(trainerRepository.save(trainerEntity)).thenReturn(trainerEntity);
        when(trainerMapper.toDto(trainerEntity)).thenReturn(trainerDTO);

        // Act
        TrainerDTO result = trainerService.updateTrainer(trainerId, trainerDTO);

        // Assert
        assertNotNull(result);
        assertEquals(trainerDTO.getFirstName(), result.getFirstName());
        assertEquals(trainerDTO.getEmail(), result.getEmail());

        verify(trainerRepository, times(1)).findById(trainerId);
        verify(trainerMapper, times(1)).toEntity(trainerDTO);
        verify(trainerRepository, times(1)).save(trainerEntity);
        verify(trainerMapper, times(1)).toDto(trainerEntity);
    }

    @Test
    void testUpdateTrainer_TrainerNotFound() {
        // Arrange
        when(trainerRepository.findById(trainerId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> trainerService.updateTrainer(trainerId, trainerDTO));
        assertEquals("Trainer not found with id " + trainerId, exception.getMessage());

        verify(trainerRepository, times(1)).findById(trainerId);
        verify(trainerMapper, never()).toEntity(any());
        verify(trainerRepository, never()).save(any());
        verify(trainerMapper, never()).toDto(any());
    }
}
