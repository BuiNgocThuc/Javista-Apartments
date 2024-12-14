package com.example.javista.dto.response.apartment;

import com.example.javista.enums.ApartmentStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

    ApartmentStatus status;

    Integer currentWaterNumber;
}
