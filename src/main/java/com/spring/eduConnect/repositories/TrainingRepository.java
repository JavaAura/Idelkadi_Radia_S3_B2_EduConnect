package com.spring.eduConnect.repositories;

import com.spring.eduConnect.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository  extends JpaRepository<Training, Long> {
}
