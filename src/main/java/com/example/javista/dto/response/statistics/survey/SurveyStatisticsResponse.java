package com.example.javista.dto.response.statistics.survey;

import com.example.javista.dto.response.survey.SurveyResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SurveyStatisticsResponse {
    SurveyResponse survey;

    Integer totalParticipants;

    Set<QuestionStatisticsResponse> questions;
}
