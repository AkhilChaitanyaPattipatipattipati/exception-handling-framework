package com.tmobile.bladerunner.exception;

public class ValidationException extends BaseException {
    public ValidationException() {
    }

    public ValidationException(String code, String systemMessage, String userMessage) {
        super(code, systemMessage, userMessage);
    }

    public ValidationException(String code, Throwable cause) {
        super(code, cause);
    }

    public ValidationException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }


}
