package com.example.javista.dto.request.userAnswer;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswerCreationRequest {
        @NotNull
        Integer userId;

        @NotNull
        Integer answerId;
}
