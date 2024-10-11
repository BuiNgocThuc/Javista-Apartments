package com.example.javista.mapper;

import com.example.javista.dto.request.survey.SurveyCreationRequest;
import com.example.javista.dto.request.survey.SurveyPatchRequest;
import com.example.javista.dto.request.survey.SurveyUpdateRequest;
import com.example.javista.dto.response.survey.SurveyResponse;
import com.example.javista.entity.Survey;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
        // mapping dto creation request to entity
        @Mapping(target = "user", ignore = true)
        Survey creationRequestToEntity(SurveyCreationRequest request);

        // mapping dto update request to entity
        @Mapping(target = "user", ignore = true)
        void updateRequestToEntity(@MappingTarget Survey survey, SurveyUpdateRequest request);

        // mapping dto patch request to entity
        @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
        void patchRequestToEntity(@MappingTarget Survey survey, SurveyPatchRequest request);

        // mapping entity to dto response
        @Mapping(source = "user.id", target = "userId")
        SurveyResponse entityToResponse(Survey survey);
}
