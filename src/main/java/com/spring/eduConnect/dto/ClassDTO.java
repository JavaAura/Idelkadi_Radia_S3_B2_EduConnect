package com.spring.eduConnect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO {
    private Long id;
    private String name;
    private String roomNumber;
    private Long trainingId;
}