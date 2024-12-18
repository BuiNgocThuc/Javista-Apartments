package com.example.javista.dto.request.report;

import jakarta.validation.constraints.NotNull;

import com.example.javista.enums.ReportStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportCreationRequest {
    @NotNull
    String content;

    @NotNull
    String title;

    @NotNull
    ReportStatus status;

    @NotNull
    Integer relationshipId;

    @NotNull
    Integer rejectionReasonId;
}
