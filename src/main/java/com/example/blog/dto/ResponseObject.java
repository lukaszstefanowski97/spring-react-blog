package com.example.blog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObject {

    @JsonProperty("status_code")
    Integer statusCode;

    @JsonProperty("message")
    String message;

    @JsonProperty("additional_info")
    String additionalInfo;

    public ResponseObject(Integer statusCode, String message, String additionalInfo) {
        this.statusCode = statusCode;
        this.message = message;
        this.additionalInfo = additionalInfo;
    }
}
