package com.example.javista.dto.request.relationship;

import jakarta.validation.constraints.NotNull;

import com.example.javista.enums.RelationshipRole;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationshipCreationRequest {
    @NotNull
    RelationshipRole role;

    @NotNull
    String apartmentId;

    @NotNull
    Integer userId;
}
