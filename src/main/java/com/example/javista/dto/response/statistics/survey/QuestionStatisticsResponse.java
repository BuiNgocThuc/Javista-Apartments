package com.example.javista.dto.response.statistics.survey;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionStatisticsResponse {
    Integer questionId;

    String content;

    Set<AnswerStatisticsResponse> answers;

    Set<OtherAnswerStatisticsResponse> otherAnswers;
}
