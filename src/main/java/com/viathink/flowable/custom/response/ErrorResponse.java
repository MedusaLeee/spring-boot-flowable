package com.viathink.flowable.custom.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private int code;
    private String error;
    public ErrorResponse( int code, String error) {
        this.code = code;
        this.error = error;
    }
}
