package com.example.javista.dto.request.item;

import jakarta.persistence.Column;
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
        String image;

        @NotNull
        String description;

        @NotNull
        Boolean isReceive;

        @NotNull
        Integer userId;
}
