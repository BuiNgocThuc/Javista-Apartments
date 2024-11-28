package com.example.javista.service.statistics;

import com.example.javista.dto.request.statistics.RevenueStatisticsRequest;
import com.example.javista.dto.response.statistics.RevenueStatisticsResponse;

import java.util.List;

public interface StatisticsService {
    RevenueStatisticsResponse getRevenueStatistics(RevenueStatisticsRequest request);
}
