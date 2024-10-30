package com.example.javista.dto.request.service;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceCreationRequest {
        @NotNull
        String name;

        @NotNull
        String description;

        @NotNull
        Integer price;
}
