package com.example.javista.dto.request.survey;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import com.example.javista.dto.request.otherAnswer.OtherAnswerCreationRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerCreationRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SurveySubmissionRequest {
    @NotNull
    List<OtherAnswerCreationRequest> otherAnswers;

    @NotNull
    List<UserAnswerCreationRequest> userAnswers;
}
