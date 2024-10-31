package com.spring.eduConnect.repositories;

import com.spring.eduConnect.entities.Training;
import com.spring.eduConnect.entities.enums.TrainingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingRepository  extends JpaRepository<Training, Long> {
    List<Training> findByTitle(String title);

    List<Training> findByLevelAndStatus(String level, TrainingStatus status);

    @Query("SELECT t FROM Training t WHERE t.startDate >= :startDate AND t.endDate <= :endDate")
    List<Training> findTrainingsWithinDateRange(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);

    Page<Training> findAll(Pageable pageable);


}
