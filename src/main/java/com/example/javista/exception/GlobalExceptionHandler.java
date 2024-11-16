package com.example.javista.exception;

import com.example.javista.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

        private static final  String MIN_ATTR = "min";

        // uncategorized exception
        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<ApiResponse> handlingUncategorizedException(Exception e) {
                log.error("exception: {}", e.getMessage());
                ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;

                return ResponseEntity.status(errorCode.getHttpStatusCode())
                        .body(
                                ApiResponse.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .build()
                        );
        }

        // defined exception
        @ExceptionHandler(value = AppException.class)
        public ResponseEntity<ApiResponse> handlingAppException(AppException e) {
                ErrorCode errorCode = e.getErrorCode();

                return ResponseEntity.status(errorCode.getHttpStatusCode())
                        .body(
                                ApiResponse.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .build()
                        );
        }

        // access denied exception
        @ExceptionHandler(value = AccessDeniedException.class)
        public ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException e) {
                ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

                return ResponseEntity.status(errorCode.getHttpStatusCode())
                        .body(
                                ApiResponse.builder()
                                        .code(errorCode.getCode())
                                        .message(errorCode.getMessage())
                                        .build()
                        );
        }

        // validation exception
        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse> handlingValidationException(MethodArgumentNotValidException e) {
                String enumKey = e.getFieldError().getDefaultMessage();
                ErrorCode errorCode;

                Map<String, Object> attributes = null;

                try {
                        errorCode = ErrorCode.valueOf(enumKey);

                        var constraintViolation = e.getBindingResult()
                                        .getAllErrors().getFirst().unwrap(ConstraintViolation.class);

                        attributes = constraintViolation.getConstraintDescriptor().getAttributes();

                } catch (IllegalArgumentException ex) {
                        errorCode = ErrorCode.INVALID_KEY;
                }

                ApiResponse apiResponse = new ApiResponse();

                String mappedMessage = Objects.nonNull(attributes)
                                ? mapAttribute(errorCode.getMessage(), attributes)
                                : errorCode.getMessage();

                apiResponse.setCode(errorCode.getCode());
                apiResponse.setMessage(mappedMessage);

                return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
        }

        private String mapAttribute(String message, Map<String, Object> attributes) {
                String minVal = String.valueOf(attributes.get(MIN_ATTR));
                return message.replace("{" + MIN_ATTR + "}", minVal);
        }
}
