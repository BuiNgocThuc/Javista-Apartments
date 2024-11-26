package com.example.javista.dto.request.item;

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
public class ItemQueryRequest extends PageRequest {
    String id;

    String description;

    String isReceived;

    String createdAt;

    String updatedAt;
}
