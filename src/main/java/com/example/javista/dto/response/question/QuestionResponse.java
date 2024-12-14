package com.example.javista.dto.response.question;

import java.time.LocalDateTime;
import java.util.List;

import com.example.javista.dto.response.answer.AnswerResponse;
import com.example.javista.dto.response.otherAnswer.OtherAnswerResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse {
    Integer id;

    String content;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updatedAt;

    Integer surveyId;

    List<AnswerResponse> answers = List.of();

    List<OtherAnswerResponse> otherAnswers = List.of();
}
