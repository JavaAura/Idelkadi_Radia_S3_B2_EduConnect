package com.spring.eduConnect.services.impl;

import com.spring.eduConnect.dto.ClassDTO;
import com.spring.eduConnect.entities.Class;
import com.spring.eduConnect.repositories.ClassRepository;
import com.spring.eduConnect.services.ClassService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository, ModelMapper modelMapper) {
        this.classRepository = classRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClassDTO createClass(ClassDTO classDTO) {
        Class classEntity = modelMapper.map(classDTO, Class.class);
        classEntity = classRepository.save(classEntity);
        return modelMapper.map(classEntity, ClassDTO.class);
    }

    @Override
    public ClassDTO updateClass(Long id, ClassDTO classDTO) {
        Class classEntity = classRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id " + id));

        modelMapper.map(classDTO, classEntity);
        classEntity = classRepository.save(classEntity);
        return modelMapper.map(classEntity, ClassDTO.class);
    }

    @Override
    public ClassDTO getClassById(Long id) {
        Class classEntity = classRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id " + id));
        return modelMapper.map(classEntity, ClassDTO.class);
    }

    @Override
    public List<ClassDTO> getAllClasses() {
        return classRepository.findAll().stream()
                .map(classEntity -> modelMapper.map(classEntity, ClassDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClass(Long id) {
        if (!classRepository.existsById(id)) {
            throw new EntityNotFoundException("Class not found with id " + id);
        }
        classRepository.deleteById(id);
    }
}
