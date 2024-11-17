package com.example.javista.dto.response.rejectionReason;

import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
