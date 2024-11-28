package com.example.javista.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.javista.dto.request.question.QuestionCreationRequest;
import com.example.javista.dto.request.question.QuestionPatchRequest;
import com.example.javista.dto.request.question.QuestionUpdateRequest;
import com.example.javista.dto.response.answer.AnswerResponse;
import com.example.javista.dto.response.otherAnswer.OtherAnswerResponse;
import com.example.javista.dto.response.question.QuestionResponse;
import com.example.javista.entity.Answer;
import com.example.javista.entity.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    // mapping dto creation request to entity
    @Mapping(target = "survey", ignore = true)
    Question creationRequestToEntity(QuestionCreationRequest request);

    // mapping dto update request to entity
    @Mapping(target = "survey", ignore = true)
    void updateRequestToEntity(@MappingTarget Question question, QuestionUpdateRequest request);

    // mapping dto patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget Question question, QuestionPatchRequest request);

    // mapping entity to dto response
    @Mapping(source = "survey.id", target = "surveyId")
    @Mapping(source = "answers", target = "answers")
    @Mapping(source = "otherAnswers", target = "otherAnswers")
    QuestionResponse entityToResponse(Question question);

    AnswerResponse entityAnswerToResponse(Answer answer);

    OtherAnswerResponse entityOtherAnswerToResponse(Answer otherAnswer);

    List<AnswerResponse> entityAnswersToResponses(Set<Answer> answers);

    List<OtherAnswerResponse> entityOtherAnswersToResponses(Set<Answer> otherAnswers);
}
