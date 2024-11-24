package com.example.javista.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    ENTITY_EXISTED(1002, "Entity already exists", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_NUMBER(1003, "Phone number must be in the VN region", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1004, "Email must be in correct format", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1005, "Date of birth must be at least {min} years ago", HttpStatus.BAD_REQUEST),
    FILE_UPLOAD_FAILED(1006, "file upload failed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1006, "User not found", HttpStatus.NOT_FOUND),
    USERNAME_EXISTED(1007, "Username already exists", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1008, "Your password is incorrect", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(1009, "You need to login", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1010, "You don't have permission", HttpStatus.FORBIDDEN),
    CONFIRM_PASSWORD_NOT_MATCH(1011, "Confirm password not match", HttpStatus.BAD_REQUEST),
    QUESTION_NOT_FOUND(1012, "Question not found", HttpStatus.NOT_FOUND),
    ANSWER_NOT_FOUND(1013, "Answer not found", HttpStatus.NOT_FOUND),
    INVALID_REGEX_FORMAT(1014, "Invalid regex format", HttpStatus.BAD_REQUEST),
    ITEM_NOT_FOUND(1015, "Item not found", HttpStatus.NOT_FOUND),
    BILL_NOT_FOUND(1016, "Bill not found", HttpStatus.NOT_FOUND),
    ;

    int code;
    String message;
    HttpStatusCode httpStatusCode;

    public void setMessage(String message) {
        this.message = message;
    }
}
