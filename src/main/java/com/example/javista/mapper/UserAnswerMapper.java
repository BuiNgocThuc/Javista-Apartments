package com.example.javista.mapper;

import com.example.javista.dto.request.userAnswer.UserAnswerCreationRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerPatchRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerUpdateRequest;
import com.example.javista.dto.response.userAnswer.UserAnswerResponse;
import com.example.javista.entity.UserAnswer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserAnswerMapper {
        // mapping dto creation request to entity
        @Mapping(target = "answer", ignore = true)
        @Mapping(target = "user", ignore = true)
        UserAnswer creationRequestToEntity(UserAnswerCreationRequest request);

        // mapping dto update request to entity
        @Mapping(target = "answer", ignore = true)
        @Mapping(target = "user", ignore = true)
        void updateRequestToEntity(@MappingTarget UserAnswer userAnswer, UserAnswerUpdateRequest request);

        // mapping dto patch request to entity
        @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
        void patchRequestToEntity(@MappingTarget UserAnswer userAnswer, UserAnswerPatchRequest request);

        // mapping entity to dto response
        @Mapping(source = "answer.id", target = "answerId")
        @Mapping(source = "user.id", target = "userId")
        UserAnswerResponse entityToResponse(UserAnswer userAnswer);
}
