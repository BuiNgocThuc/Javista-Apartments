package com.example.javista.dto.request.userAnswer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswerPatchRequest {
                Integer userId;

                Integer answerId;
}
