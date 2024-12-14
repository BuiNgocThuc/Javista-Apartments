package com.example.javista.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageRequest {
    int page = 1;
    int pageSize = 10;
    String sort = "createdAt";
}
