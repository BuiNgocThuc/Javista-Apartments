package com.example.javista.dto.request.apartment;

import com.example.javista.enums.ApartmentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApartmentPatchRequest {
    Float area;

    String description;

    Integer floorNumber;

    Integer apartmentNumber;

    ApartmentStatus status;

    Integer currentWaterNumber;
}
