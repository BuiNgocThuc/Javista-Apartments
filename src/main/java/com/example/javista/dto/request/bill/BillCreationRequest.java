package com.example.javista.dto.request.bill;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

import com.example.javista.enums.BillStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NotNull
public class BillCreationRequest {
    @NotNull
    String monthly;

    @NotNull
    Float totalPrice;

    @NotNull
    Integer oldWater;

    @NotNull
    Integer newWater;

    @NotNull
    LocalDateTime waterReadingDate;

    @NotNull
    BillStatus status;

    @NotNull
    Integer relationshipId;
}
