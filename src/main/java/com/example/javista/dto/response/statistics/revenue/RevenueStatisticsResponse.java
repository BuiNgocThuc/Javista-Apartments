package com.example.javista.dto.response.statistics.revenue;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RevenueStatisticsResponse {
    List<MonthlyRevenueStatisticsResponse> monthlyRevenueStatistics;
}
