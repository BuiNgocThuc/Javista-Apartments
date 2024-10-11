package com.example.javista.dto.request.item;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemUpdateRequest {
        String image;

        String description;

        Byte isReceive;

        Integer userId;
}
