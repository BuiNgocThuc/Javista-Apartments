package com.example.javista.dto.response.statistics.survey;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerStatisticsResponse {
    Integer answerId;

    String content;

    Integer count;

    Boolean isMostSelected;
}
