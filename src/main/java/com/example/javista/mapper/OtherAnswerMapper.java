package com.example.javista.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.javista.dto.request.otherAnswer.OtherAnswerCreationRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerPatchRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerUpdateRequest;
import com.example.javista.dto.response.otherAnswer.OtherAnswerResponse;
import com.example.javista.entity.OtherAnswer;

@Mapper(componentModel = "spring")
public interface OtherAnswerMapper {
    // mapping dto creation request to entity
    @Mapping(target = "question", ignore = true)
    @Mapping(target = "user", ignore = true)
    OtherAnswer creationRequestToEntity(OtherAnswerCreationRequest request);

    // mapping dto update request to entity
    @Mapping(target = "question", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateRequestToEntity(@MappingTarget OtherAnswer otherAnswer, OtherAnswerUpdateRequest request);

    // mapping dto patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget OtherAnswer otherAnswer, OtherAnswerPatchRequest request);

    // mapping entity to dto response
    @Mapping(source = "question.id", target = "questionId")
    @Mapping(source = "user.id", target = "userId")
    OtherAnswerResponse entityToResponse(OtherAnswer otherAnswer);
}
