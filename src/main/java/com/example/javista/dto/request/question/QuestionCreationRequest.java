package com.example.javista.dto.request.question;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionCreationRequest {
        @NotNull
        String content;

        @NotNull
        Integer surveyId;
}
