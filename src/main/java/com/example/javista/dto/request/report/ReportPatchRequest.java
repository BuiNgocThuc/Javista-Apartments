package com.example.javista.dto.request.report;

import com.example.javista.enums.ReportStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportPatchRequest {
    String content;

    String title;

    ReportStatus status;

    Integer relationshipId;

    Integer rejectionReasonId;
}
