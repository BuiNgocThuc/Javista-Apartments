package com.example.javista.dto.request.apartment;

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

        String status;
}
