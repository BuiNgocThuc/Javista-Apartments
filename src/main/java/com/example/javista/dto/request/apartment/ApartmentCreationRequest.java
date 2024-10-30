package com.example.javista.dto.request.apartment;

import com.example.javista.enums.ApartmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
}
