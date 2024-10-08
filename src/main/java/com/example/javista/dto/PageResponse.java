package com.example.javista.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
        int totalPages;
        long totalElements;
        int pageSize;
        int currentPage;

        @Builder.Default
        List<T> data = Collections.emptyList();
}
