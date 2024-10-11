package com.example.javista.dto.request.answer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerUpdateRequest {
        String content;

        Integer questionId;
}
