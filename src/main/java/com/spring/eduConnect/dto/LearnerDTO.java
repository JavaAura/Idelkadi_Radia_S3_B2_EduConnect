package com.spring.eduConnect.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LearnerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String level;
    private Long trainingId;
    private Long classId;

}