package com.example.javista.dto.response.answer;

import com.example.javista.entity.Question;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerResponse {
        Integer id;

        String content;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;

        Integer questionId;
}
