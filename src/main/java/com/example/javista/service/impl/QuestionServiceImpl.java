package com.example.javista.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.javista.dto.request.question.QuestionCreationRequest;
import com.example.javista.dto.request.question.QuestionPatchRequest;
import com.example.javista.dto.request.question.QuestionQueryRequest;
import com.example.javista.dto.request.question.QuestionUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.question.QuestionResponse;
import com.example.javista.entity.Question;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.QuestionMapper;
import com.example.javista.repository.QuestionRepository;
import com.example.javista.repository.SurveyRepository;
import com.example.javista.service.QuestionService;
import com.example.javista.utils.QueryUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionServiceImpl implements QuestionService {
    QuestionMapper questionMapper;
    QuestionRepository questionRepository;

    SurveyRepository surveyRepository;

    FilterSpecification<Question> filterSpecification;

    @Override
    public PageResponse<QuestionResponse> getQuestions(QuestionQueryRequest query) {
        // Pagination and Sorting
        Pageable pageable = QueryUtils.getPagination(query);

        // Filtering and searching by specification
        Specification<Question> spec =
                filterSpecification.filteringBySpecification(QueryUtils.getFilterCriterion(query));

        var pageData = questionRepository.findAll(spec, pageable);

        return QueryUtils.buildPageResponse(pageData, pageable, questionMapper::entityToResponse);
    }

    @Override
    public QuestionResponse getQuestionById(Integer id) {
        return questionMapper.entityToResponse(
                questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question Not Found")));
    }

    @Override
    public QuestionResponse createQuestion(QuestionCreationRequest request) {

        // check existence of question's content
        if (questionRepository.existsByContent(request.getContent())) {
            ErrorCode.ENTITY_EXISTED.setMessage("Question's content already exists");
            throw new AppException(ErrorCode.ENTITY_EXISTED);
        }

        Question question = questionMapper.creationRequestToEntity(request);

        question.setSurvey(surveyRepository
                .findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey Not Found")));

        return questionMapper.entityToResponse(questionRepository.save(question));
    }

    @Override
    public QuestionResponse updateQuestion(Integer id, QuestionUpdateRequest request) {
        Question question =
                questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question Not Found"));

        question.setSurvey(surveyRepository
                .findById(request.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey Not Found")));

        questionMapper.updateRequestToEntity(question, request);
        return questionMapper.entityToResponse(questionRepository.save(question));
    }

    @Override
    public QuestionResponse patchQuestion(Integer id, QuestionPatchRequest request) {
        Question question =
                questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question Not Found"));

        questionMapper.patchRequestToEntity(question, request);
        return questionMapper.entityToResponse(questionRepository.save(question));
    }

    @Override
    public void deleteQuestion(Integer id) {
        Question question =
                questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question Not Found"));

        question.setDeletedAt(LocalDateTime.now());
        questionRepository.save(question);
    }
}
