package com.example.javista.service;

import com.example.javista.dto.request.userAnswer.UserAnswerCreationRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerPatchRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerQueryRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.userAnswer.UserAnswerResponse;

public interface UserAnswerService {
    PageResponse<UserAnswerResponse> getUserAnswers(UserAnswerQueryRequest query);

    UserAnswerResponse getUserAnswerById(Integer id);

    UserAnswerResponse createUserAnswer(UserAnswerCreationRequest request);

    UserAnswerResponse updateUserAnswer(Integer id, UserAnswerUpdateRequest request);

    UserAnswerResponse patchUserAnswer(Integer id, UserAnswerPatchRequest request);

    void deleteUserAnswer(Integer id);
}
