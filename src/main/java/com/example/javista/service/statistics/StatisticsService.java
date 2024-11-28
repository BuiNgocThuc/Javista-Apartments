package com.example.javista.service.statistics;

import com.example.javista.dto.request.statistics.RevenueStatisticsRequest;
import com.example.javista.dto.response.statistics.revenue.RevenueStatisticsResponse;
import com.example.javista.dto.response.statistics.survey.SurveyStatisticsResponse;

public interface StatisticsService {
    RevenueStatisticsResponse getRevenueStatistics(RevenueStatisticsRequest request);
}
