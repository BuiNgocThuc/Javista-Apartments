package com.example.javista.dto.response.statistics.survey;

import java.util.Set;

import com.example.javista.dto.response.survey.SurveyResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
