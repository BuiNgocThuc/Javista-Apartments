package com.example.javista.service;

import com.example.javista.dto.request.answer.AnswerCreationRequest;
import com.example.javista.dto.request.answer.AnswerQueryRequest;
import com.example.javista.dto.request.answer.AnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.answer.AnswerResponse;

public interface AnswerService {
        PageResponse<AnswerResponse> getAnswers(AnswerQueryRequest query);

        AnswerResponse getAnswerById(String id);

        AnswerResponse createAnswer(AnswerCreationRequest request);

        AnswerResponse updateAnswer(String id, AnswerUpdateRequest request);

        void deleteAnswer(String id);
}
