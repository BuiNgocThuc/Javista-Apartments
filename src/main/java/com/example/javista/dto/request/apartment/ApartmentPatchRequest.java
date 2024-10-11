package com.example.javista.dto.request.apartment;

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

        String status;
}
