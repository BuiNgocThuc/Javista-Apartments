package com.example.javista.dto.request.setting;

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

        String systemStatus;

        Float roomPricePerM2;

        Float waterPricePerM3;

        Float roomVat;

        Integer waterVat;

        Integer envProtectionTax;
}
