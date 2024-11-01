package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.ClassDTO;
import com.spring.eduConnect.entities.Class;
import com.spring.eduConnect.repositories.ClassRepository;
import com.spring.eduConnect.services.impl.ClassServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.mockito.ArgumentMatchers.any;
import javax.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassServiceImplTest {

    @Mock
    private ClassRepository classRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClassServiceImpl classService;

    private Class classEntity;
    private ClassDTO classDTO;

    @BeforeEach
    void setUp() {
        classEntity = new Class();
        classEntity.setId(1L);
        classEntity.setName("Math");

        classDTO = new ClassDTO();
        classDTO.setId(1L);
        classDTO.setName("Math");
    }

    @Test
    void testCreateClass() {
        when(modelMapper.map(classDTO, Class.class)).thenReturn(classEntity);
        when(classRepository.save(classEntity)).thenReturn(classEntity);
        when(modelMapper.map(classEntity, ClassDTO.class)).thenReturn(classDTO);

        ClassDTO result = classService.createClass(classDTO);

        assertNotNull(result);
        assertEquals(classDTO.getName(), result.getName());
        verify(classRepository, times(1)).save(classEntity);
    }


    @Test
    void testUpdateClass_NonExistingId() {
        Long classId = 1L;

        when(classRepository.findById(classId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> classService.updateClass(classId, classDTO));
        verify(classRepository, never()).save(any(Class.class));
    }

    @Test
    void testGetClassById_ExistingId() {
        Long classId = 1L;

        when(classRepository.findById(classId)).thenReturn(Optional.of(classEntity));
        when(modelMapper.map(classEntity, ClassDTO.class)).thenReturn(classDTO);

        ClassDTO result = classService.getClassById(classId);

        assertNotNull(result);
        assertEquals(classDTO.getName(), result.getName());
        verify(classRepository, times(1)).findById(classId);
    }

    @Test
    void testGetClassById_NonExistingId() {
        Long classId = 1L;

        when(classRepository.findById(classId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> classService.getClassById(classId));
    }

    @Test
    void testGetAllClasses() {
        when(classRepository.findAll()).thenReturn(Arrays.asList(classEntity));
        when(modelMapper.map(classEntity, ClassDTO.class)).thenReturn(classDTO);

        List<ClassDTO> result = classService.getAllClasses();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(classRepository, times(1)).findAll();
    }

    @Test
    void testDeleteClass_ExistingId() {
        Long classId = 1L;

        when(classRepository.existsById(classId)).thenReturn(true);

        classService.deleteClass(classId);

        verify(classRepository, times(1)).deleteById(classId);
    }

    @Test
    void testDeleteClass_NonExistingId() {
        Long classId = 1L;

        when(classRepository.existsById(classId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> classService.deleteClass(classId));
        verify(classRepository, never()).deleteById(classId);
    }

    @Test
    void testGetAllClassesPaginated() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Class> classPage = new PageImpl<>(Arrays.asList(classEntity));

        when(classRepository.findAll(pageable)).thenReturn(classPage);
        when(modelMapper.map(classEntity, ClassDTO.class)).thenReturn(classDTO);

        Page<ClassDTO> result = classService.getAllClassesPaginated(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(classRepository, times(1)).findAll(pageable);
    }
}
