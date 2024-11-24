package com.example.javista.dto.request.rejectionReason;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RejectionReasonUpdateRequest {
    String content;

    Integer reportId;
}
