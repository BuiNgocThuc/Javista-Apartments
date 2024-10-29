package com.example.javista.dto.request.report;

import com.example.javista.enums.ReportStatus;
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

        ReportStatus status;

        Integer relationshipId;

        Integer rejectionReasonId;
}
