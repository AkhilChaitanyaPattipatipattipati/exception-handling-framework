package com.tmobile.bladerunner.exception;

public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String code, String systemMessage, String userMessage) {
        super(code, systemMessage, userMessage);
    }

    public EntityNotFoundException(String code, Throwable cause) {
        super(code, cause);
    }

    public EntityNotFoundException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
