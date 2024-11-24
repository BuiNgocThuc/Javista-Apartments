package com.example.javista.dto.request.relationship;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationshipQueryRequest {
    String id;

    String role;

    String apartmentId;

    String userId;

    String createdAt;

    String updatedAt;
}
