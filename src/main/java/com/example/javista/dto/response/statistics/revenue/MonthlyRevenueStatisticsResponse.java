package com.example.javista.dto.response.statistics.revenue;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyRevenueStatisticsResponse {
    String month;

    Double revenue;
}
