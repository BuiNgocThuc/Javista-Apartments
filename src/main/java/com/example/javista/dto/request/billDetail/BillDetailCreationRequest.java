package com.example.javista.dto.request.billDetail;

import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillDetailCreationRequest {
    @NotNull
    Float price;

    @NotNull
    Integer billId;

    @NotNull
    Integer serviceId;
}
