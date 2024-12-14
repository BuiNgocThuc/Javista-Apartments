package com.example.javista.dto.request.apartment;

import jakarta.validation.constraints.NotNull;

import com.example.javista.enums.ApartmentStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentCreationRequest {
    @NotNull
    String id;

    @NotNull
    Float area;

    @NotNull
    String description;

    @NotNull
    Integer floorNumber;

    @NotNull
    Integer apartmentNumber;

    @NotNull
    ApartmentStatus status;

    @NotNull
    Integer currentWaterNumber;
}
