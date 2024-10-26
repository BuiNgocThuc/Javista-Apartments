package com.example.javista.dto.request.apartment;

import com.example.javista.dto.request.PageRequest;
import com.example.javista.enums.ApartmentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentQueryRequest extends PageRequest {
        String id;

        String area;

        String description;

        String floorNumber;

        String apartmentNumber;

        ApartmentStatus status;
}
