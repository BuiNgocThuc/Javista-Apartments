package com.example.javista.dto.response.bill;

import java.time.LocalDateTime;

import com.example.javista.enums.BillStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {
    Integer id;

    String monthly;

    Float totalPrice;

    Integer oldWater;

    Integer newWater;

    LocalDateTime waterReadingDate;

    BillStatus status;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    Integer relationshipId;
}
