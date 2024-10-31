package com.spring.eduConnect.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Class name is required")
    private String name;

    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @OneToMany(mappedBy = "classEntity")
    private List<Learner> learners;


    @OneToMany(mappedBy = "classEntity")
    private List<Trainer> trainers ;
}