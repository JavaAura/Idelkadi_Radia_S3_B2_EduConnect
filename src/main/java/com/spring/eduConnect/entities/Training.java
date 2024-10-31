package com.spring.eduConnect.entities;

import com.spring.eduConnect.entities.enums.TrainingStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Level is required")
    private String level;

    @NotBlank(message = "Prerequisites are required")
    private String prerequisites;

    @NotNull(message = "Minimum capacity is required")
    private int minimumCapacity;

    @NotNull(message = "Maximum capacity is required")
    private int maximumCapacity;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private TrainingStatus status;

    @OneToMany(mappedBy = "training")
    private List<Learner> learners;

    @OneToMany(mappedBy = "training")
    private List<Trainer> trainers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Learner> getLearners() {
        return learners;
    }

    public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }
}
