package com.example.javista.dto.request.billDetail;

import com.example.javista.dto.request.PageRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillDetailQueryRequest extends PageRequest {

        String id;

        String billId;

        String price;

        String quantity;

        String createdAt;

        String updatedAt;
}
