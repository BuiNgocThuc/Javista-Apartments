package com.example.javista.dto.request.relationship;

import com.example.javista.dto.request.PageRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelationshipQueryRequest extends PageRequest {
    String id;

    String role;

    String apartmentId;

    String user_Id;

    String createdAt;

    String updatedAt;
}
