package com.example.javista.dto.response.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMSResponse {
    @JsonProperty("CodeResult")
    private String codeResult;

    @JsonProperty("CountRegenerate")
    private int countRegenerate;

    @JsonProperty("SMSID")
    private String smsId;
}
