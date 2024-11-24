package com.example.javista.dto.response.relationship;

import java.time.LocalDateTime;

import com.example.javista.enums.RelationshipRole;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationshipResponse {
    Integer id;

    RelationshipRole role;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    Integer userId;

    String apartmentId;
}
