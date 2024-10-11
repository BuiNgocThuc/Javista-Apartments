package com.example.javista.dto.request.answer;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.MappingTarget;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerCreationRequest {
        String content;

        String questionId;
}
