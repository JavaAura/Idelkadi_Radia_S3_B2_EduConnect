package com.spring.eduConnect.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class TrainerDTO extends UserDTO {
        private String specialty;
        private Long classId;

        public Long getClassId() {
                return classId;
        }

        public void setClassId(Long classId) {
                this.classId = classId;
        }
}