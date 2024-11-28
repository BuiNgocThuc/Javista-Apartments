package com.example.javista.dto.response.statistics;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RevenueStatisticsResponse {
    List<MonthlyRevenueStatisticsResponse> monthlyRevenueStatistics;
}
