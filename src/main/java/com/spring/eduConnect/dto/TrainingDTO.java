package com.spring.eduConnect.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO {
    private Long id;
    private String title;
    private String level;
    private String prerequisites;
    private int minCapacity;
    private int maxCapacity;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}