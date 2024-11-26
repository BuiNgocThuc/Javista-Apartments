package com.example.javista.dto.request.survey;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FullSurveyCreationRequest {
    @NotNull
    String title;

    @NotNull
    Integer totalQuestions;

    @NotNull
    LocalDateTime startDate;

    @NotNull
    LocalDateTime endDate;

    @NotNull
    Integer userId;

    @NotNull
    List<QuestionRequest> questions;

}