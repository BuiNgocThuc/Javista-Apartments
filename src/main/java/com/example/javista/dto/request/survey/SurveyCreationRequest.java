package com.example.javista.dto.request.survey;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SurveyCreationRequest {
        String title;

        Integer totalQuestions;

        Integer userId;
}
