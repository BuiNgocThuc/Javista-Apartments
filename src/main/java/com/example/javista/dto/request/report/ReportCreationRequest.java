package com.example.javista.dto.request.report;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportCreationRequest {
        String content;

        String title;

        String status;

        Integer relationshipId;

        Integer rejectionReasonId;
}
