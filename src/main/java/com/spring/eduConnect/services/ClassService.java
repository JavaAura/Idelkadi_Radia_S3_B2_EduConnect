package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.ClassDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassService {
    ClassDTO createClass(ClassDTO classDTO);
    ClassDTO updateClass(Long id, ClassDTO classDTO);
    ClassDTO getClassById(Long id);
    List<ClassDTO> getAllClasses();
    void deleteClass(Long id);
    Page<ClassDTO> getAllClassesPaginated(Pageable pageable);
}
