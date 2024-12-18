package com.example.javista.dto.request.rejectionReason;

import com.example.javista.dto.request.PageRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RejectionReasonQueryRequest extends PageRequest {
    String id;

    String content;

    String createdAt;

    String updatedAt;
}
