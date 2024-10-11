package com.example.javista.dto.request.otherAnswer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtherAnswerCreationRequest {
        String content;

        Integer questionId;

        Integer userId;
}
