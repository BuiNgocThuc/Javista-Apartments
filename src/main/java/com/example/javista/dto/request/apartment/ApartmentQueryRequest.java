package com.example.javista.dto.request.apartment;

import com.example.javista.dto.request.PageRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentQueryRequest extends PageRequest {
        String id;

        Float area;

        Integer floorNumber;

        Integer apartmentNumber;

        String status;
}
