package com.example.javista.dto.request.setting;

import jakarta.validation.constraints.NotNull;

import com.example.javista.enums.SystemStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingCreationRequest {
    @NotNull
    String currentMonthly;

    @NotNull
    SystemStatus systemStatus;

    @NotNull
    Float roomPricePerM2;

    @NotNull
    Float waterPricePerM3;

    @NotNull
    Float roomVat;

    @NotNull
    Integer waterVat;

    @NotNull
    Integer envProtectionTax;
}
