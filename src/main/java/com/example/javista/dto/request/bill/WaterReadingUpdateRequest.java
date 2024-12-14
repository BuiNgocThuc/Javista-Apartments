package com.example.javista.dto.request.bill;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaterReadingUpdateRequest {
    Integer billId;
    Integer newWaterIndex;
    LocalDateTime readingDate;
}
