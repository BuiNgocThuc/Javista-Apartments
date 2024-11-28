package com.example.javista.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javista.dto.request.statistics.RevenueStatisticsRequest;
import com.example.javista.dto.response.ApiResponse;
import com.example.javista.dto.response.statistics.RevenueStatisticsResponse;
import com.example.javista.service.statistics.StatisticsService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StatisticsController {
    StatisticsService statisticsService;

    @GetMapping("/revenue")
    public ApiResponse<RevenueStatisticsResponse> getRevenueStatistics(
            @ModelAttribute RevenueStatisticsRequest request) {
        log.info("Get revenue statistics: {}", request);
        return ApiResponse.<RevenueStatisticsResponse>builder()
                .result(statisticsService.getRevenueStatistics(request))
                .build();
    }
}
