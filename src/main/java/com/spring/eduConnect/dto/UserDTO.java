package com.spring.eduConnect.dto;

import lombok.Data;

@Data
public abstract class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long trainingId;

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }
}
