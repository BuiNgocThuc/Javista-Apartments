package com.example.javista.dto.request.answer;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.MappingTarget;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerCreationRequest {
        @NotNull
        String content;

        @NotNull
        Integer questionId;
}
