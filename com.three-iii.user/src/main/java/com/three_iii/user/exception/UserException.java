package com.three_iii.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    public UserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getStatus();
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
            "errorCode=" + errorCode +
            ", message='" + message + '\'' +
            ", httpStatus=" + httpStatus +
            '}';
    }
}