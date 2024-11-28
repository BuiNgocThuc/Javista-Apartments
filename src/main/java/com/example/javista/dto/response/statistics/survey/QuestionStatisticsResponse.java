package com.example.javista.dto.response.statistics.survey;

import com.example.javista.dto.response.otherAnswer.OtherAnswerResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

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
