package com.example.javista.dto.request.setting;

import com.example.javista.enums.SystemStatus;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingCreationRequest {
        String currentMonthly;

        SystemStatus systemStatus;

        Float roomPricePerM2;

        Float waterPricePerM3;

        Float roomVat;

        Integer waterVat;

        Integer envProtectionTax;
}
