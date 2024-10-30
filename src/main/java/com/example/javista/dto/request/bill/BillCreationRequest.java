package com.example.javista.dto.request.bill;

import com.example.javista.enums.BillStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
