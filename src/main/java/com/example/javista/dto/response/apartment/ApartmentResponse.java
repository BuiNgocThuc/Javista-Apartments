package com.example.javista.dto.response.apartment;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentResponse {
        String id;

        Float area;

        String description;

        Integer floorNumber;

        Integer apartmentNumber;

        String status;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;
}
