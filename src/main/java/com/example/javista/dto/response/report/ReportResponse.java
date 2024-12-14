package com.example.javista.dto.response.report;

import java.time.LocalDateTime;

import com.example.javista.enums.ReportStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportResponse {
    Integer id;

    String content;

    String title;

    ReportStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updatedAt;

    Integer relationshipId;

    Integer rejectionReasonId;
}
