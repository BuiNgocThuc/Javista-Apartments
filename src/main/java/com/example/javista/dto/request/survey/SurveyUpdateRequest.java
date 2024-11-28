package com.example.javista.dto.request.survey;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SurveyUpdateRequest {
    String title;

    Integer totalQuestions;

    Integer userId;

    LocalDateTime startDate;

    LocalDateTime endDate;

    List<QuestionRequest> questions;
}
