package com.example.javista.dto.response.relationship;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationshipResponse {
        Integer id;

        String role;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;

        Integer userId;

        Integer apartmentId;
}
