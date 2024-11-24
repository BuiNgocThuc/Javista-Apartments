package com.example.javista.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.javista.dto.request.survey.SurveyCreationRequest;
import com.example.javista.dto.request.survey.SurveyPatchRequest;
import com.example.javista.dto.request.survey.SurveyQueryRequest;
import com.example.javista.dto.request.survey.SurveyUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.survey.SurveyResponse;

public interface SurveyService {
    @PreAuthorize("hasRole('ADMIN')")
    PageResponse<SurveyResponse> getSurveys(SurveyQueryRequest query);

    SurveyResponse getSurveyById(Integer id);

    SurveyResponse createSurvey(SurveyCreationRequest request);

    SurveyResponse updateSurvey(Integer id, SurveyUpdateRequest request);

    SurveyResponse patchSurvey(Integer id, SurveyPatchRequest request);

    void deleteSurvey(Integer id);
}
