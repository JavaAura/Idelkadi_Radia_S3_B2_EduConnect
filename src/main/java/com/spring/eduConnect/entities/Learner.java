package com.spring.eduConnect.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Learner")
public class Learner extends User {

    private String level;



}
