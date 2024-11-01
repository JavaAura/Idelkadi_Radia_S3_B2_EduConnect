package com.spring.eduConnect.services;

import com.spring.eduConnect.dto.ClassDTO;
import com.spring.eduConnect.entities.Class;
import com.spring.eduConnect.repositories.ClassRepository;
import com.spring.eduConnect.services.impl.ClassServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class ClassServiceIntegrationTest {

    @Autowired
    private ClassServiceImpl classService;

    @Autowired
    private ClassRepository classRepository;

    private ClassDTO classDTO;

    @BeforeEach
    public void setUp() {
        classDTO = new ClassDTO();
        classDTO.setName("Math Class");
        classDTO.setRoomNumber("12");
    }

    @Test
    public void testCreateClass() {
        ClassDTO createdClass = classService.createClass(classDTO);

        assertThat(createdClass).isNotNull();
        assertThat(createdClass.getName()).isEqualTo(classDTO.getName());

        Class retrievedClass = classRepository.findById(createdClass.getId()).orElse(null);
        assertThat(retrievedClass).isNotNull();
        assertThat(retrievedClass.getName()).isEqualTo(classDTO.getName());
    }

    @Test
    public void testUpdateClass() {
        ClassDTO createdClass = classService.createClass(classDTO);
        createdClass.setName("Updated Math Class");

        ClassDTO updatedClass = classService.updateClass(createdClass.getId(), createdClass);

        assertThat(updatedClass).isNotNull();
        assertThat(updatedClass.getName()).isEqualTo("Updated Math Class");
    }

    @Test
    public void testGetClassById() {
        ClassDTO createdClass = classService.createClass(classDTO);

        ClassDTO retrievedClass = classService.getClassById(createdClass.getId());

        assertThat(retrievedClass).isNotNull();
        assertThat(retrievedClass.getId()).isEqualTo(createdClass.getId());
    }

    @Test
    public void testDeleteClass() {
        ClassDTO createdClass = classService.createClass(classDTO);

        classService.deleteClass(createdClass.getId());

        assertThat(classRepository.findById(createdClass.getId())).isEmpty();
    }

    @Test
    public void testGetTotalClasses() {
        ClassDTO classDTO1 = new ClassDTO();
        classDTO1.setName("Math Class");
        classDTO1.setRoomNumber("12");

        ClassDTO classDTO2 = new ClassDTO();
        classDTO2.setName("Science Class");
        classDTO2.setRoomNumber("14");

        classService.createClass(classDTO1);
        classService.createClass(classDTO2);

        long totalClasses = classService.getAllClasses().size();

        assertThat(totalClasses).isEqualTo(2);
    }

}
