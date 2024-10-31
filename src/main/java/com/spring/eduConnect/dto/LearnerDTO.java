package com.spring.eduConnect.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class LearnerDTO extends UserDTO {
    private String level;
    private Long classId; // Assuming class has an ID to reference
}