package com.example.javista.dto.request.payment;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public record ExtraData(Integer billId) {
}