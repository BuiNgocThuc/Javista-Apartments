package com.example.javista.dto.response.billDetail;

import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
