package com.example.javista.dto.request.otherAnswer;

import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtherAnswerCreationRequest {
    @NotNull
    String content;

    @NotNull
    Integer questionId;

    @NotNull
    Integer userId;
}
