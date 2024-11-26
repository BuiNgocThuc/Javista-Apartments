package com.example.javista.dto.request.survey;

import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SurveyCreationRequest {
    @NotNull
    String title;

    @NotNull
    Integer totalQuestions;

    @NotNull
    Integer userId;
}
