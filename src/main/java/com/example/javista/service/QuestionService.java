package com.example.javista.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.javista.dto.request.question.QuestionCreationRequest;
import com.example.javista.dto.request.question.QuestionPatchRequest;
import com.example.javista.dto.request.question.QuestionQueryRequest;
import com.example.javista.dto.request.question.QuestionUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.question.QuestionResponse;

public interface QuestionService {
    @PreAuthorize("hasRole('RESIDENT')")
    PageResponse<QuestionResponse> getQuestions(QuestionQueryRequest query);

    QuestionResponse getQuestionById(Integer id);

    QuestionResponse createQuestion(QuestionCreationRequest request);

    QuestionResponse updateQuestion(Integer id, QuestionUpdateRequest request);

    QuestionResponse patchQuestion(Integer id, QuestionPatchRequest request);

    void deleteQuestion(Integer id);
}
