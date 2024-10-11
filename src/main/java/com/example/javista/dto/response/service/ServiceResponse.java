package com.example.javista.dto.response.service;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponse {
        Integer id;

        String name;

        String description;

        Float price;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;
}
