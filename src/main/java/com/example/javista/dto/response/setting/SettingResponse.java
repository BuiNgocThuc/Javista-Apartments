package com.example.javista.dto.response.setting;

import com.example.javista.enums.SystemStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingResponse {
    String currentMonthly;

    SystemStatus systemStatus;

    Float roomPricePerM2;

    Float waterPricePerM3;

    Float roomVat;

    Integer waterVat;

    Integer envProtectionTax;
}
