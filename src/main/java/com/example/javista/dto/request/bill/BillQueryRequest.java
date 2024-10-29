package com.example.javista.dto.request.bill;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillQueryRequest {

        String id;

        String monthly;

        String totalPrice;

        String oldWater;

        String newWater;

        String waterReadingDate;

        String status;

        String relationshipId;
}
