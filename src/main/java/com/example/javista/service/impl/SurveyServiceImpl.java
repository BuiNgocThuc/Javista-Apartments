package com.example.javista.service.impl;

import com.example.javista.dto.request.survey.*;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.survey.SurveyResponse;
import com.example.javista.entity.*;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.OtherAnswerMapper;
import com.example.javista.mapper.SurveyMapper;
import com.example.javista.mapper.UserAnswerMapper;
import com.example.javista.repository.*;
import com.example.javista.service.SurveyService;
import com.example.javista.utils.QueryUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SurveyServiceImpl implements SurveyService {
    SurveyMapper surveyMapper;
    OtherAnswerMapper otherAnswerMapper;
    UserAnswerMapper userAnswerMapper;

    SurveyRepository surveyRepository;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    UserRepository userRepository;
    OtherAnswerRepository otherAnswerRepository;
    UserAnswerRepository userAnswerRepository;

    FilterSpecification<Survey> filterSpecification;

    @Override
    public PageResponse<SurveyResponse> getSurveys(SurveyQueryRequest query) {
        // Pagination and Sorting
        Pageable pageable = QueryUtils.getPagination(query);

        // Filtering and searching by specification
        Specification<Survey> spec = filterSpecification.filteringBySpecification(QueryUtils.getFilterCriterion(query));

        var pageData = surveyRepository.findAll(spec, pageable);

        return QueryUtils.buildPageResponse(pageData, pageable, surveyMapper::entityToResponse);
    }

    @Override
    public SurveyResponse getSurveyById(Integer id) {
        return surveyMapper.entityToResponse(
            surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey Not Found")));
    }

    @Override
    public SurveyResponse createSurvey(SurveyCreationRequest request) {
        Survey survey = surveyMapper.creationRequestToEntity(request);

        survey.setUser(
            userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));
        return surveyMapper.entityToResponse(surveyRepository.save(survey));
    }

    @Override
    public SurveyResponse updateSurvey(Integer id, SurveyUpdateRequest request) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey Not Found"));

        survey.setUser(
            userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));

        surveyMapper.updateRequestToEntity(survey, request);
        return surveyMapper.entityToResponse(surveyRepository.save(survey));
    }

    @Override
    public SurveyResponse patchSurvey(Integer id, SurveyPatchRequest request) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey Not Found"));

        surveyMapper.patchRequestToEntity(survey, request);
        return surveyMapper.entityToResponse(surveyRepository.save(survey));
    }

    @Override
    public void deleteSurvey(Integer id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey Not Found"));

        survey.setDeletedAt(LocalDateTime.now());
        surveyRepository.save(survey);
    }

    @Override
    public Void createFullSurvey(FullSurveyCreationRequest request) {
        // Find the user first
        User user = userRepository
            .findById(request.getUserId())
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Create survey and set user
        Survey survey = surveyMapper.fullCreationRequestToEntity(request);
        survey.setUser(user);

        // Prepare lists to store
        List<Question> questions = new ArrayList<>();

        // Process questions and answers
        for (QuestionRequest questionRequest : request.getQuestions()) {
            Question question = surveyMapper.questionDtoToEntity(questionRequest);
            question.setSurvey(survey); // Set survey reference

            List<Answer> answers = new ArrayList<>();
            for (AnswerRequest answerRequest : questionRequest.getAnswers()) {
                Answer answer = surveyMapper.answerDtoToEntity(answerRequest);
                answer.setQuestion(question); // Set question reference
                answers.add(answer);
            }
            question.setAnswers(new HashSet<>(answers));
            questions.add(question);
        }

        survey.setQuestions(new HashSet<>(questions));

        // Save the survey
        surveyRepository.save(survey);

        return null;
    }

    @Override
    public Void submitSurvey(SurveySubmissionRequest request) {
        request.getOtherAnswers().forEach(otherAnswer -> {
            // find user by id
            OtherAnswer otherAnswerEntity = otherAnswerMapper.creationRequestToEntity(otherAnswer);
            otherAnswerEntity.setUser(userRepository
                .findById(otherAnswer.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
            otherAnswerEntity.setQuestion(questionRepository
                .findById(otherAnswer.getQuestionId())
                .orElseThrow(() -> new AppException(ErrorCode.QUESTION_NOT_FOUND)));

            otherAnswerRepository.save(otherAnswerEntity);
        });

        request.getUserAnswers().forEach(userAnswer -> {
            // find user by id
            UserAnswer userAnswerEntity = userAnswerMapper.creationRequestToEntity(userAnswer);
            userAnswerEntity.setUser(userRepository
                .findById(userAnswer.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
            userAnswerEntity.setAnswer(answerRepository
                .findById(userAnswer.getAnswerId())
                .orElseThrow(() -> new AppException(ErrorCode.ANSWER_NOT_FOUND)));

            userAnswerRepository.save(userAnswerEntity);
        });

        return null;
    }
}
