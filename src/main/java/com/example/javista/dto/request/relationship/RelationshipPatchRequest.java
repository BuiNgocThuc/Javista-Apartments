package com.example.javista.dto.request.relationship;

import com.example.javista.enums.RelationshipRole;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelationshipPatchRequest {
    RelationshipRole role;

    String apartmentId;

    Integer userId;
}
