package com.example.javista.dto.response.userAnswer;

import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswerResponse {
    Integer id;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    Integer userId;

    Integer answerId;
}
