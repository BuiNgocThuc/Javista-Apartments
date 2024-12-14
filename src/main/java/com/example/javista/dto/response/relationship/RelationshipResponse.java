package com.example.javista.dto.response.relationship;

import java.time.LocalDateTime;

import com.example.javista.dto.response.apartment.ApartmentResponse;
import com.example.javista.dto.response.user.UserResponse;
import com.example.javista.enums.RelationshipRole;
import com.fasterxml.jackson.annotation.JsonFormat;

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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updatedAt;

    Integer userId;

    String apartmentId;

    UserResponse user;

    ApartmentResponse apartment;
}
