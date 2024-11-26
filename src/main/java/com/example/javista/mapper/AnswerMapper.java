package com.example.javista.mapper;

import org.mapstruct.*;

import com.example.javista.dto.request.answer.AnswerCreationRequest;
import com.example.javista.dto.request.answer.AnswerPatchRequest;
import com.example.javista.dto.request.answer.AnswerUpdateRequest;
import com.example.javista.dto.response.answer.AnswerResponse;
import com.example.javista.entity.Answer;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    // mapping dto creation request to entity
    @Mapping(target = "question", ignore = true)
    Answer creationRequestToEntity(AnswerCreationRequest request);

    // mapping dto update request to entity
    @Mapping(target = "question", ignore = true)
    void updateRequestToEntity(@MappingTarget Answer answer, AnswerUpdateRequest request);

    // mapping dto patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget Answer answer, AnswerPatchRequest request);

    // mapping entity to dto response
    @Mapping(source = "question.id", target = "questionId")
    AnswerResponse entityToResponse(Answer answer);
}
