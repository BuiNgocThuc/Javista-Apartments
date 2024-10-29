package com.example.javista.dto.request.bill;

import com.example.javista.enums.BillStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillUpdateRequest {
        String monthly;

        Float totalPrice;

        Integer oldWater;

        Integer newWater;

        LocalDateTime waterReadingDate;

        BillStatus status;

        Integer relationshipId;

}
