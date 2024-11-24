package com.example.javista.dto.request.survey;

import com.example.javista.dto.request.otherAnswer.OtherAnswerCreationRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerCreationRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
