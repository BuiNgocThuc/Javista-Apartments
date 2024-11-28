package com.example.javista.service.impl;

import com.example.javista.dto.request.statistics.RevenueStatisticsRequest;
import com.example.javista.dto.response.statistics.MonthlyRevenueStatisticsResponse;
import com.example.javista.dto.response.statistics.RevenueStatisticsResponse;
import com.example.javista.repository.BillRepository;
import com.example.javista.service.statistics.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class StatisticsServiceImpl implements StatisticsService {

    BillRepository billRepository;

    @Override
    public RevenueStatisticsResponse getRevenueStatistics(RevenueStatisticsRequest request) {
        log.info("start month: {}, end month: {}", request.getStartMonth(), request.getEndMonth());
        List<MonthlyRevenueStatisticsResponse> monthlyRevenueStatistics =
            new ArrayList<>();
            List<Object[]> revenueStatistics =
                billRepository.getRevenueStatistics(request.getStartMonth(), request.getEndMonth());

            log.info(String.valueOf(revenueStatistics.size()));
            // log revenue statistics
            revenueStatistics.forEach(stat -> {
                log.info("Revenue statistics: {}", stat[0] + " - " + stat[1]);
                monthlyRevenueStatistics.add(
                    MonthlyRevenueStatisticsResponse.builder()
                        .month(stat[0].toString())
                        .revenue((Double) stat[1])
                        .build()
                );
            });


        return RevenueStatisticsResponse.builder()
            .monthlyRevenueStatistics(monthlyRevenueStatistics)
            .build();
    }
}
