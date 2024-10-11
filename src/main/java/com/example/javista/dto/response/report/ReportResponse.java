package com.example.javista.dto.response.report;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportResponse {
        Integer id;

        String content;

        String title;

        String status;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;

        Integer relationshipId;

        Integer rejectionReasonId;
}
