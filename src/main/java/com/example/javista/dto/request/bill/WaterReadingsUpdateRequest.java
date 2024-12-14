package com.example.javista.dto.request.bill;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaterReadingsUpdateRequest {
    List<WaterReadingUpdateRequest> waterReadings;
}
