package com.example.javista.dto.request.survey;

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
public class SurveyQueryRequest extends PageRequest {
    String title;

    String startDate;

    String endDate;

    String createdDate;

    String updatedDate;
}
