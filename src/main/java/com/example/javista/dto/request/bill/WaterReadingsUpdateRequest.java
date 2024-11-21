package com.example.javista.dto.request.bill;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaterReadingsUpdateRequest {
    List<WaterReadingUpdateRequest> waterReadings;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class WaterReadingUpdateRequest {
        Integer billId;
        Integer newWaterIndex;
        LocalDateTime readingDate;
    }
}
