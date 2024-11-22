package com.example.javista.dto.request.relationship;

import com.example.javista.enums.RelationshipRole;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationshipUpdateRequest {
    RelationshipRole role;

    String apartmentId;

    Integer userId;
}
