package com.example.javista.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T>  implements Serializable {
        int totalPages;
        long totalElements;
        int pageSize;
        int currentPage;

        @Builder.Default
        List<T> data = Collections.emptyList();
}
