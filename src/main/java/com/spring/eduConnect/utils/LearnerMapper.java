package com.spring.eduConnect.utils;

import com.spring.eduConnect.dto.LearnerDTO;
import com.spring.eduConnect.dto.TrainerDTO;
import com.spring.eduConnect.entities.Learner;
import com.spring.eduConnect.entities.Trainer;
import com.spring.eduConnect.entities.Training;
import com.spring.eduConnect.entities.Class;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LearnerMapper {

//    @Mapping(source = "training.id", target = "trainingId")
//    @Mapping(source = "classEntity.id", target = "classId")
//    LearnerDTO toDto(Learner learner);
//
//    @Mapping(source = "trainingId", target = "training.id")
//    @Mapping(source = "classId", target = "classEntity.id")
//    Learner toEntity(LearnerDTO learnerDTO);
}