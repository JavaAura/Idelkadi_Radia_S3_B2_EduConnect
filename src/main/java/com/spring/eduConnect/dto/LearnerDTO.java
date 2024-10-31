package com.spring.eduConnect.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class LearnerDTO extends UserDTO {
    private String level;
    private Long classId;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}