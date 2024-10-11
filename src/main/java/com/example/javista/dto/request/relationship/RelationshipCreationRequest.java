package com.example.javista.dto.request.relationship;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationshipCreationRequest {
        String role;

        String apartmentId;

        Integer userId;
}
