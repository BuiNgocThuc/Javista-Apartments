package com.example.javista.dto.request.question;

import jakarta.validation.constraints.Pattern;

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
public class QuestionQueryRequest extends PageRequest {
    @Pattern(regexp = "^(eq|lt|gt):\\d+$", message = "INVALID_REGEX_FORMAT")
    String id;

    @Pattern(regexp = "^(eq|lt|gt):\\d+$", message = "INVALID_REGEX_FORMAT")
    String content;

    String createdAt;

    String updatedAt;

    String survey_Id;
}
