package com.example.javista.dto.response.survey;

import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SurveyResponse {
    Integer id;

    String title;

    LocalDateTime startDate;

    LocalDateTime endDate;

    Integer totalQuestions;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    Integer userId;
}
