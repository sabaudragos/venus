package com.vegaone.venus.exceptionhandler;

import lombok.Data;

@Data
public class ErrorCode {
    private String code;
    private String message;

    public ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
