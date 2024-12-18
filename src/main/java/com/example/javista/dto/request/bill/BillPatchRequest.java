package com.example.javista.dto.request.bill;

import java.time.LocalDateTime;

import com.example.javista.enums.BillStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillPatchRequest {
    String monthly;

    Float totalPrice;

    Integer oldWater;

    Integer newWater;

    LocalDateTime waterReadingDate;

    BillStatus status;

    String relationshipId;
}
