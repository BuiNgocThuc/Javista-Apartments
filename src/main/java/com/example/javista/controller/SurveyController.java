package com.example.javista.controller;

import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.survey.*;
import com.example.javista.dto.response.ApiResponse;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.survey.SurveyResponse;
import com.example.javista.service.SurveyService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SurveyController {
    SurveyService surveyService;

    // test postman Http: http://localhost:8080/javista/surveys

    // Query
    @GetMapping
    PageResponse<SurveyResponse> getSurveys(@ModelAttribute SurveyQueryRequest query) {
        return surveyService.getSurveys(query);
    }

    // Query by id
    @GetMapping("/{id}")
    SurveyResponse getSurveyById(@PathVariable Integer id) {
        return surveyService.getSurveyById(id);
    }

    // Create
    @PostMapping
    SurveyResponse createSurvey(@RequestBody SurveyCreationRequest request) {
        return surveyService.createSurvey(request);
    }

    // Update
    @PutMapping("/{id}")
    SurveyResponse updateSurvey(@PathVariable Integer id, @RequestBody SurveyUpdateRequest request) {
        return surveyService.updateSurvey(id, request);
    }

    // Delete
    @DeleteMapping("/{id}")
    void deleteSurvey(@PathVariable Integer id) {
        surveyService.deleteSurvey(id);
    }

    // Patch
    @PatchMapping("/{id}")
    void patchSurvey(@PathVariable Integer id, @RequestBody SurveyPatchRequest request) {
        surveyService.patchSurvey(id, request);
    }

    // Create full survey
    @PostMapping("/full-create")
    ApiResponse<Void> createFullSurvey(@RequestBody FullSurveyCreationRequest request) {
        return ApiResponse.<Void>builder()
                .message("Survey created successfully")
                .result(surveyService.createFullSurvey(request))
                .build();
    }

    // Submit survey
    @PostMapping("/submit")
    ApiResponse<Void> submitSurvey(@RequestBody SurveySubmissionRequest request) {
        return ApiResponse.<Void>builder()
                .message("Survey submitted successfully")
                .result(surveyService.submitSurvey(request))
                .build();
    }
}
