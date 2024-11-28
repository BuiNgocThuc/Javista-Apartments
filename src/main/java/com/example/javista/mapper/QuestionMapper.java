package com.example.javista.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.*;

import com.example.javista.dto.request.question.QuestionCreationRequest;
import com.example.javista.dto.request.question.QuestionPatchRequest;
import com.example.javista.dto.request.question.QuestionUpdateRequest;
import com.example.javista.dto.response.answer.AnswerResponse;
import com.example.javista.dto.response.otherAnswer.OtherAnswerResponse;
import com.example.javista.dto.response.question.QuestionResponse;
import com.example.javista.entity.Answer;
import com.example.javista.entity.OtherAnswer;
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
    //    @Mappings({
    //        @Mapping(source = "survey.id", target = "surveyId"),
    //        @Mapping(source = "answers", target = "answers"),
    //        @Mapping(source = "otherAnswers", target = "otherAnswers"),
    //    })
    @Mapping(source = "survey.id", target = "surveyId")
    @Mapping(target = "answers", qualifiedByName = "mapAnswersWithQuestionId")
    @Mapping(target = "otherAnswers", qualifiedByName = "mapOtherAnswersWithQuestionId")
    QuestionResponse entityToResponse(Question question);

    @Named("mapAnswersWithQuestionId")
    default List<AnswerResponse> mapAnswersWithQuestionId(Set<Answer> answers) {
        return answers == null
                ? List.of()
                : answers.stream().map(this::entityAnswerToResponse).collect(Collectors.toList());
    }

    @Named("mapOtherAnswersWithQuestionId")
    default List<OtherAnswerResponse> mapOtherAnswersWithQuestionId(Set<OtherAnswer> otherAnswers) {
        return otherAnswers == null
                ? List.of()
                : otherAnswers.stream().map(this::entityOtherAnswerToResponse).collect(Collectors.toList());
    }

    @Mapping(source = "question.id", target = "questionId")
    AnswerResponse entityAnswerToResponse(Answer answer);

    @Mapping(source = "question.id", target = "questionId")
    @Mapping(source = "user.id", target = "userId")
    OtherAnswerResponse entityOtherAnswerToResponse(OtherAnswer otherAnswer);

    List<AnswerResponse> entityAnswersToResponses(Set<Answer> answers);

    List<OtherAnswerResponse> entityOtherAnswersToResponses(Set<Answer> otherAnswers);
}
