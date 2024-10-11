package com.example.javista.dto.response.otherAnswer;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtherAnswerResponse {
        Integer id;

        String content;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;

        Integer questionId;

        Integer userId;
}
