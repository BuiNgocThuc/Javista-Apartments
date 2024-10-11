package com.example.javista.dto.request.setting;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingPatchRequest {
        String currentMonthly;

        String systemStatus;

        Float roomPricePerM2;

        Float waterPricePerM3;

        Float roomVat;

        Integer waterVat;

        Integer envProtectionTax;
}
