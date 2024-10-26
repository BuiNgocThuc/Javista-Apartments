package com.example.javista.dto.request.apartment;

import com.example.javista.enums.ApartmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentCreationRequest {
        @NotBlank
        String id;

        Float area;

        String description;

        Integer floorNumber;

        Integer apartmentNumber;

        ApartmentStatus status;
}
