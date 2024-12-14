package com.example.javista.dto.request.otherAnswer;

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
public class OtherAnswerQueryRequest extends PageRequest {

    String id;

    String content;

    String createdAt;

    String updatedAt;

    String questionId;
}
