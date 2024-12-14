package com.example.javista.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.javista.dto.request.survey.*;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.survey.SurveyResponse;

public interface SurveyService {
    @PreAuthorize("hasRole('ADMIN')")
    PageResponse<SurveyResponse> getSurveys(SurveyQueryRequest query);

    SurveyResponse getSurveyById(Integer id);

    @PreAuthorize("hasRole('ADMIN')")
    SurveyResponse createSurvey(SurveyCreationRequest request);

    @PreAuthorize("hasRole('ADMIN')")
    SurveyResponse updateSurvey(Integer id, SurveyUpdateRequest request);

    @PreAuthorize("hasRole('ADMIN')")
    SurveyResponse patchSurvey(Integer id, SurveyPatchRequest request);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteSurvey(Integer id);

    @PreAuthorize("hasRole('ADMIN')")
    Void createFullSurvey(FullSurveyCreationRequest request);

    @PreAuthorize("hasRole('RESIDENT')")
    Void submitSurvey(SurveySubmissionRequest request);
}
