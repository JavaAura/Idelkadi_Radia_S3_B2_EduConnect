package com.spring.eduConnect.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;
    private Long trainingId;
    private Long classId;
}