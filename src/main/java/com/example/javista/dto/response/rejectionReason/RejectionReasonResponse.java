package com.example.javista.dto.response.rejectionReason;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RejectionReasonResponse {
        Integer id;

        String content;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;

        Integer reportId;
}
