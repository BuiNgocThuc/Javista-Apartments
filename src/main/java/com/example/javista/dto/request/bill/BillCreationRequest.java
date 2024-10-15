package com.example.javista.dto.request.bill;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillCreationRequest {
        String monthly;

        Float totalPrice;

        Integer oldWater;

        Integer newWater;

        LocalDateTime waterReadingDate;

        String status;

        Integer relationshipId;
}
