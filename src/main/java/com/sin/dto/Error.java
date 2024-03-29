package com.sin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    /**
     * 错误码
     */
    @JsonProperty("Code")
    private String code;

    /**
     * 错误信息
     */
    @JsonProperty("Message")
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
