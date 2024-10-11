package com.example.javista.dto.request.answer;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerQueryRequest {
        Integer id;

        LocalDateTime createdAt;
}
