package com.example.javista.dto.request.answer;

import com.example.javista.dto.request.PageRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerQueryRequest extends PageRequest {
        String id;

        String content;

        String createdAt;

        String updatedAt;

        String questionId;
}
