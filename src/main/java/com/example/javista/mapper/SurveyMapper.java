package com.example.javista.mapper;

import com.example.javista.dto.request.otherAnswer.OtherAnswerCreationRequest;
import com.example.javista.dto.request.survey.*;
import com.example.javista.dto.request.userAnswer.UserAnswerCreationRequest;
import com.example.javista.entity.*;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.javista.dto.response.survey.SurveyResponse;

import java.util.List;

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

    // Mapping FullSurveyCreationRequest to Survey entity
    @Mapping(target = "user", ignore = true) // Assuming User is managed separately
    @Mapping(target = "questions", source = "questions")
    Survey fullCreationRequestToEntity(FullSurveyCreationRequest request);

    // Mapping nested Question DTO to Question entity
    @Mapping(target = "survey", ignore = true) // Assuming survey is set elsewhere
    @Mapping(target = "answers", source = "answers")
    Question questionDtoToEntity(QuestionRequest question);

    // Mapping nested Answer DTO to Answer entity
    @Mapping(target = "question", ignore = true) // Assuming question is set elsewhere
    Answer answerDtoToEntity(AnswerRequest answer);

    // Helper method to map a list of Questions
    List<Question> mapQuestions(List<QuestionRequest> questions);

    // Helper method to map a list of Answers
    List<Answer> mapAnswers(List<AnswerRequest> answers);
}
