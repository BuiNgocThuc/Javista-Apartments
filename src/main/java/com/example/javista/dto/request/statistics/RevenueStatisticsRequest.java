package com.example.javista.dto.request.statistics;

import com.example.javista.validator.StatisticsDateConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@StatisticsDateConstraint
public class RevenueStatisticsRequest {
    // 2024-09
    String startMonth;

    // 2024-12
    String endMonth;
}
