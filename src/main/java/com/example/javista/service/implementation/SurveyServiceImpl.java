package com.example.javista.service.implementation;

import com.example.javista.dto.request.survey.SurveyCreationRequest;
import com.example.javista.dto.request.survey.SurveyPatchRequest;
import com.example.javista.dto.request.survey.SurveyQueryRequest;
import com.example.javista.dto.request.survey.SurveyUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.survey.SurveyResponse;
import com.example.javista.entity.Survey;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.SurveyMapper;
import com.example.javista.repository.SurveyRepository;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.SurveyService;
import com.example.javista.utils.QueryUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class SurveyServiceImpl implements SurveyService {
        SurveyMapper surveyMapper;
        SurveyRepository surveyRepository;

        UserRepository userRepository;

        FilterSpecification<Survey> filterSpecification;

        @Override
        public PageResponse<SurveyResponse> getSurveys(SurveyQueryRequest query) {
                // Pagination and Sorting
                Pageable pageable = QueryUtils.getPagination(query);

                //Filtering and searching by specification
                Specification<Survey> spec = filterSpecification.filteringBySpecification(
                          QueryUtils.getFilterCriterion(query)
                );

                var pageData = surveyRepository.findAll(spec, pageable);

                return QueryUtils.buildPageResponse(pageData, pageable, surveyMapper::entityToResponse);
        }

        @Override
        public SurveyResponse getSurveyById(Integer id) {
                return surveyMapper.entityToResponse(surveyRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Survey Not Found")));
        }

        @Override
        public SurveyResponse createSurvey(SurveyCreationRequest request) {
                Survey survey = surveyMapper.creationRequestToEntity(request);

                survey.setUser(userRepository.findById(request.getUserId())
                                .orElseThrow(() -> new RuntimeException("User Not Found")));
                return surveyMapper.entityToResponse(surveyRepository.save(survey));
        }

        @Override
        public SurveyResponse updateSurvey(Integer id, SurveyUpdateRequest request) {
                Survey survey = surveyRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Survey Not Found"));

                survey.setUser(userRepository.findById(request.getUserId())
                                .orElseThrow(() -> new RuntimeException("User Not Found")));

                surveyMapper.updateRequestToEntity(survey, request);
                return surveyMapper.entityToResponse(surveyRepository.save(survey));
        }

        @Override
        public SurveyResponse patchSurvey(Integer id, SurveyPatchRequest request) {
                Survey survey = surveyRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Survey Not Found"));

                surveyMapper.patchRequestToEntity(survey, request);
                return surveyMapper.entityToResponse(surveyRepository.save(survey));
        }

        @Override
        public void deleteSurvey(Integer id) {
                Survey survey = surveyRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Survey Not Found"));

                survey.setDeletedAt(LocalDateTime.now());
                surveyRepository.save(survey);
        }
}
