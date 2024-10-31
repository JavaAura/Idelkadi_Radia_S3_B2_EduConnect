package com.spring.eduConnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class TrainerDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String specialty;
        private Long trainingId;
        private Long classId;
}