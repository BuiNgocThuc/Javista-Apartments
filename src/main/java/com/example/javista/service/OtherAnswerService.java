package com.example.javista.service;

import com.example.javista.dto.request.otherAnswer.OtherAnswerCreationRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerPatchRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerQueryRequest;
import com.example.javista.dto.request.otherAnswer.OtherAnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.otherAnswer.OtherAnswerResponse;

public interface OtherAnswerService {
    PageResponse<OtherAnswerResponse> getOtherAnswers(OtherAnswerQueryRequest query);

    OtherAnswerResponse getOtherAnswerById(Integer id);

    OtherAnswerResponse createOtherAnswer(OtherAnswerCreationRequest request);

    OtherAnswerResponse updateOtherAnswer(Integer id, OtherAnswerUpdateRequest request);

    OtherAnswerResponse patchOtherAnswer(Integer id, OtherAnswerPatchRequest request);

    void deleteOtherAnswer(Integer id);
}
