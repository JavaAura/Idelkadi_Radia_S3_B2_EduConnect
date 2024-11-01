package com.spring.eduConnect.repositories;

import com.spring.eduConnect.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    boolean existsByName(String name);
}
