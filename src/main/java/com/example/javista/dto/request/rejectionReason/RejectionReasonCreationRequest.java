package com.example.javista.dto.request.rejectionReason;

import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RejectionReasonCreationRequest {
    @NotNull
    String content;

    @NotNull
    Integer reportId;
}
