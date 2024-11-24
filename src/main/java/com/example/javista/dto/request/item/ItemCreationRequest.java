package com.example.javista.dto.request.item;

import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCreationRequest {

    @NotNull
    String description;

    @NotNull
    Integer userId;
}
