package com.example.javista.dto.response.billDetail;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillDetailResponse {
        Integer id;

        Float price;

        LocalDateTime createdAt;

        LocalDateTime updatedAt;

        Integer billId;

        Integer serviceId;
}
