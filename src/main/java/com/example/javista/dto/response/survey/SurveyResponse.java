package com.example.javista.dto.response.survey;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

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
