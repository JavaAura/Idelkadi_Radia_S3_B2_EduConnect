package com.spring.eduConnect.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Trainer")
public class Trainer extends User {

    private String specialty;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classEntity;
}
