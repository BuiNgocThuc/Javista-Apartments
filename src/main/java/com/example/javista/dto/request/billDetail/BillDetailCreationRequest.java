package com.example.javista.dto.request.billDetail;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillDetailCreationRequest {
        Float price;

        Integer billId;

        Integer serviceId;
}
