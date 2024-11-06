package com.spring.eduConnect.controllers;

import com.spring.eduConnect.dto.ClassDTO;
import com.spring.eduConnect.services.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Class Management", description = "Operations related to classes")
public class ClassController {
    private final ClassService classService;
    private static final Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @Operation(summary = "Create a new class", description = "Creates a new class in the system.")
    @ApiResponse(responseCode = "201", description = "Class created successfully")
    @PostMapping
    public ResponseEntity<ClassDTO> createClass(@RequestBody ClassDTO classDTO) {
        logger.info("Creating class with name: {}", classDTO.getName());
        ClassDTO createdClass = classService.createClass(classDTO);
        logger.info("Class created with ID: {}", createdClass.getId());
        return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing class", description = "Updates an existing class based on ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Class updated successfully"),
            @ApiResponse(responseCode = "404", description = "Class not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClassDTO> updateClass(
            @PathVariable @Parameter(description = "ID of the class to be updated") Long id,
                @RequestBody ClassDTO classDTO) {
            logger.info("Updating class with ID: {}", id);
        ClassDTO updatedClass = classService.updateClass(id, classDTO);
        logger.info("Class updated with ID: {}", updatedClass.getId());
        return ResponseEntity.ok(updatedClass);
    }

    @Operation(summary = "Get class by ID", description = "Fetches a class based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Class fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Class not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getClassById(
            @PathVariable @Parameter(description = "ID of the class to retrieve") Long id) {
        logger.info("Fetching class with ID: {}", id);
        ClassDTO classDTO = classService.getClassById(id);
        logger.info("Fetched class: {}", classDTO);
        return ResponseEntity.ok(classDTO);
    }

    @Operation(summary = "Get all classes", description = "Fetches all classes from the system.")
    @GetMapping("/all")
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        logger.info("Fetching all classes");
        List<ClassDTO> classes = classService.getAllClasses();
        logger.info("Fetched {} classes", classes.size());
        return ResponseEntity.ok(classes);
    }

    @Operation(summary = "Delete a class by ID", description = "Deletes a class based on its ID.")
    @ApiResponse(responseCode = "204", description = "Class deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        logger.info("Deleting class with ID: {}", id);
        classService.deleteClass(id);
        logger.info("Class deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all classes paginated", description = "Fetches all classes in a paginated format.")
    @GetMapping("/paginated")
    public ResponseEntity<Page<ClassDTO>> getAllClassesPaginated(Pageable pageable) {
        logger.info("Fetching paginated classes, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<ClassDTO> classes = classService.getAllClassesPaginated(pageable);
        logger.info("Fetched {} classes on page: {}", classes.getContent().size(), pageable.getPageNumber());
        return ResponseEntity.ok(classes);
    }
}
