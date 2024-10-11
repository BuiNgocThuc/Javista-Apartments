package com.example.javista.dto.response.item;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponse {
        Integer id;

        String image;

        String description;

        Boolean isReceive;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;

        Integer userId;
}
