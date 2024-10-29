package com.spring.eduConnect.entities;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public abstract class User {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String fName;

    @NotBlank(message = "Last name is required")
    @NotNull
    private String lName;

    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
}
