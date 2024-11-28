package com.example.javista.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.javista.dto.request.statistics.RevenueStatisticsRequest;
import com.example.javista.dto.response.statistics.revenue.MonthlyRevenueStatisticsResponse;
import com.example.javista.dto.response.statistics.revenue.RevenueStatisticsResponse;
import com.example.javista.dto.response.statistics.survey.AnswerStatisticsResponse;
import com.example.javista.dto.response.statistics.survey.QuestionStatisticsResponse;
import com.example.javista.dto.response.statistics.survey.SurveyStatisticsResponse;
import com.example.javista.entity.Survey;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.mapper.StatisticsMapper;
import com.example.javista.mapper.SurveyMapper;
import com.example.javista.repository.BillRepository;
import com.example.javista.repository.QuestionRepository;
import com.example.javista.repository.SurveyRepository;
import com.example.javista.service.statistics.StatisticsService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class StatisticsServiceImpl implements StatisticsService {

    BillRepository billRepository;

    SurveyRepository surveyRepository;

    QuestionRepository questionRepository;

    StatisticsMapper statisticsMapper;
    SurveyMapper surveyMapper;

    @Override
    public RevenueStatisticsResponse getRevenueStatistics(RevenueStatisticsRequest request) {
        log.info("start month: {}, end month: {}", request.getStartMonth(), request.getEndMonth());
        List<MonthlyRevenueStatisticsResponse> monthlyRevenueStatistics = new ArrayList<>();
        List<Object[]> revenueStatistics =
                billRepository.getRevenueStatistics(request.getStartMonth(), request.getEndMonth());

        log.info(String.valueOf(revenueStatistics.size()));
        // log revenue statistics
        revenueStatistics.forEach(stat -> {
            log.info("Revenue statistics: {}", stat[0] + " - " + stat[1]);
            monthlyRevenueStatistics.add(MonthlyRevenueStatisticsResponse.builder()
                    .month(stat[0].toString())
                    .revenue((Double) stat[1])
                    .build());
        });

        return RevenueStatisticsResponse.builder()
                .monthlyRevenueStatistics(monthlyRevenueStatistics)
                .build();
    }

    @Override
    public SurveyStatisticsResponse getSurveyStatistics(Integer surveyId) {
        Survey survey =
                surveyRepository.findById(surveyId).orElseThrow(() -> new AppException(ErrorCode.SURVEY_NOT_FOUND));

        List<Object[]> result = questionRepository.getAnswerStatisticsAndOtherAnswerStatistics(surveyId);

        Map<Integer, List<Object[]>> groupedByQuestionId =
                result.stream().collect(Collectors.groupingBy(stat -> (Integer) stat[0]));

        Set<QuestionStatisticsResponse> questionStatistics = groupedByQuestionId.values().stream()
                .map(statisticsMapper::mapToQuestionStatisticsResponse)
                .collect(Collectors.toSet());

        // find most selected answer for each question
        questionStatistics.forEach(question -> question.getAnswers().stream()
                .max(Comparator.comparingInt(AnswerStatisticsResponse::getCount))
                .ifPresent(mostSelectedAnswer -> mostSelectedAnswer.setIsMostSelected(true)));

        Integer totalParticipants = surveyRepository.getTotalParticipants(surveyId);

        return SurveyStatisticsResponse.builder()
                .survey(surveyMapper.entityToResponse(survey))
                .totalParticipants(totalParticipants)
                .questions(questionStatistics)
                .build();
    }
}
