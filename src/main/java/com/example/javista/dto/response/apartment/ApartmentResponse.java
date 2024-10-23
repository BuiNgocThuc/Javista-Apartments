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

        String area;

        String description;

        String floorNumber;

        String apartmentNumber;

        String status;

//        String createdAt;
//
//        String updatedAt;
}
