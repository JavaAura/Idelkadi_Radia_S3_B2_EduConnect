package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.TrainingDTO;
import com.spring.eduConnect.entities.Training;
import com.spring.eduConnect.repositories.TrainingRepository;
import com.spring.eduConnect.services.impl.TrainingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingServiceImplTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    private TrainingDTO trainingDTO;
    private Training trainingEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trainingDTO = new TrainingDTO();
        trainingDTO.setTitle("Java Basics");
        trainingDTO.setLevel("Beginner");

        trainingEntity = new Training();
        trainingEntity.setId(1L);
        trainingEntity.setTitle("Java Basics");
        trainingEntity.setLevel("Beginner");
    }

    @Test
    void testCreateTraining_Success() {
        // Arrange
        when(modelMapper.map(trainingDTO, Training.class)).thenReturn(trainingEntity);
        when(trainingRepository.save(trainingEntity)).thenReturn(trainingEntity);
        when(modelMapper.map(trainingEntity, TrainingDTO.class)).thenReturn(trainingDTO);

        // Act
        TrainingDTO result = trainingService.createTraining(trainingDTO);

        // Assert
        assertNotNull(result);
        assertEquals(trainingDTO.getTitle(), result.getTitle());
        assertEquals(trainingDTO.getLevel(), result.getLevel());

        verify(modelMapper, times(1)).map(trainingDTO, Training.class);
        verify(trainingRepository, times(1)).save(trainingEntity);
        verify(modelMapper, times(1)).map(trainingEntity, TrainingDTO.class);
    }
    @Test
    void testGetTrainingById_Success() {
        // Arrange
        when(trainingRepository.findById(1L)).thenReturn(Optional.of(trainingEntity));
        when(modelMapper.map(trainingEntity, TrainingDTO.class)).thenReturn(trainingDTO);

        // Act
        TrainingDTO result = trainingService.getTrainingById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(trainingDTO.getTitle(), result.getTitle());
        assertEquals(trainingDTO.getLevel(), result.getLevel());

        verify(trainingRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(trainingEntity, TrainingDTO.class);
    }
    @Test
    void testUpdateTraining_TrainingNotFound() {
        // Arrange
        TrainingDTO updatedTrainingDTO = new TrainingDTO();
        updatedTrainingDTO.setTitle("Advanced Java");
        updatedTrainingDTO.setLevel("Advanced");

        when(trainingRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            trainingService.updateTraining(1L, updatedTrainingDTO);
        });

        assertEquals("Training not found with id 1", exception.getMessage());

        verify(trainingRepository, times(1)).findById(1L);
        verify(modelMapper, never()).map(updatedTrainingDTO, Training.class); // ne doit pas être appelé
        verify(trainingRepository, never()).save(any(Training.class)); // ne doit pas être appelé
    }
    @Test
    void testDeleteTraining_Success() {
        // Arrange
        Long trainingId = 1L;
        when(trainingRepository.existsById(trainingId)).thenReturn(true);

        // Act
        trainingService.deleteTraining(trainingId);

        // Assert
        verify(trainingRepository, times(1)).existsById(trainingId);
        verify(trainingRepository, times(1)).deleteById(trainingId);
    }
    @Test
    void testDeleteTraining_TrainingNotFound() {
        // Arrange
        Long trainingId = 1L;
        when(trainingRepository.existsById(trainingId)).thenReturn(false);

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            trainingService.deleteTraining(trainingId);
        });

        assertEquals("Training not found with id 1", exception.getMessage());

        verify(trainingRepository, times(1)).existsById(trainingId);
        verify(trainingRepository, never()).deleteById(trainingId); // ne doit pas être appelé
    }

}
