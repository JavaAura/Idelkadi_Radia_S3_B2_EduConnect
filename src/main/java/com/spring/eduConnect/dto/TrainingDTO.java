package com.spring.eduConnect.dto;

import com.spring.eduConnect.entities.enums.TrainingStatus;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainingDTO {
    private Long id;
    private String title;
    private String level;
    private String prerequisites;
    private int minCapacity;
    private int maxCapacity;
    private LocalDate startDate;
    private LocalDate endDate;
    private TrainingStatus status;
}