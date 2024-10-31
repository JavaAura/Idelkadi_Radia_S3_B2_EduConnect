package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.ClassDTO;
import com.spring.eduConnect.services.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ClassController {
    private final ClassService classService;
    private static final Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping
    public ResponseEntity<ClassDTO> createClass(@RequestBody ClassDTO classDTO) {
        logger.info("Creating class with name: {}", classDTO.getName());
        ClassDTO createdClass = classService.createClass(classDTO);
        logger.info("Class created with ID: {}", createdClass.getId());
        return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDTO> updateClass(@PathVariable Long id, @RequestBody ClassDTO classDTO) {
        logger.info("Updating class with ID: {}", id);
        ClassDTO updatedClass = classService.updateClass(id, classDTO);
        logger.info("Class updated with ID: {}", updatedClass.getId());
        return ResponseEntity.ok(updatedClass);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long id) {
        logger.info("Fetching class with ID: {}", id);
        ClassDTO classDTO = classService.getClassById(id);
        logger.info("Fetched class: {}", classDTO);
        return ResponseEntity.ok(classDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        logger.info("Fetching all classes");
        List<ClassDTO> classes = classService.getAllClasses();
        logger.info("Fetched {} classes", classes.size());
        return ResponseEntity.ok(classes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        logger.info("Deleting class with ID: {}", id);
        classService.deleteClass(id);
        logger.info("Class deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ClassDTO>> getAllClassesPaginated(Pageable pageable) {
        logger.info("Fetching paginated classes, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<ClassDTO> classes = classService.getAllClassesPaginated(pageable);
        logger.info("Fetched {} classes on page: {}", classes.getContent().size(), pageable.getPageNumber());
        return ResponseEntity.ok(classes);
    }
}
