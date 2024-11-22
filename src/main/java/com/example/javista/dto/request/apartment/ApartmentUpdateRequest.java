package com.example.javista.dto.request.apartment;

import com.example.javista.enums.ApartmentStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentUpdateRequest {
    Float area;

    String description;

    Integer floorNumber;

    Integer apartmentNumber;

    ApartmentStatus status;
}
