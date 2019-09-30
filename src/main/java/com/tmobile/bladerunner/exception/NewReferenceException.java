package com.tmobile.bladerunner.exception;

public class NewReferenceException extends BaseException {
    public NewReferenceException() {
    }

    public NewReferenceException(String code, String systemMessage, String userMessage) {
        super(code, systemMessage, userMessage);
    }

    public NewReferenceException(String code, Throwable cause) {
        super(code, cause);
    }

    public NewReferenceException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
