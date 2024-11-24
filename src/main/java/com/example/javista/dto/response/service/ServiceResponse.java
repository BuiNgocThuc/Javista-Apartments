package com.example.javista.dto.response.service;

import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
