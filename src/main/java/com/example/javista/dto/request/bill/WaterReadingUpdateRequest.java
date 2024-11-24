package com.example.javista.dto.request.bill;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaterReadingUpdateRequest {
    Integer billId;
    Integer newWaterIndex;
    LocalDateTime readingDate;
}
