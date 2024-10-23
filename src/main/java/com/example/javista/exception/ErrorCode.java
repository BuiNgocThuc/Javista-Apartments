package com.example.javista.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
        UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
        INVALID_KEY(1001, "Invalid message key"),
        ENTITY_EXISTED(1002, "Entity already exists"),
        INVALID_PHONE_NUMBER(1003, "Phone number must be in the VN region"),
        INVALID_EMAIL(1004, "Email must be in correct format"),
        INVALID_DOB(1005, "Date of birth must be at least {min} years ago"),
        ;

        int code;
        String message;

        public void setMessage(String message) {
                this.message = message;
        }
}
