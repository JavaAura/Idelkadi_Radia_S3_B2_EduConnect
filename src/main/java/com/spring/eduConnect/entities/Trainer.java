package com.spring.eduConnect.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("Trainer")
public class Trainer extends User {

    @NotBlank(message = "Specialty is required")
    private String specialty;

}
