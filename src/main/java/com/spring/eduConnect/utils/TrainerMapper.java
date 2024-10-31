package com.spring.eduConnect.utils;

import com.spring.eduConnect.dto.TrainerDTO;
import com.spring.eduConnect.entities.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "classEntity.id", target = "classId")
    TrainerDTO toDto(Trainer trainer);

    @Mapping(source = "trainingId", target = "training.id")
    @Mapping(source = "classId", target = "classEntity.id")
    Trainer toEntity(TrainerDTO trainerDTO);
}
