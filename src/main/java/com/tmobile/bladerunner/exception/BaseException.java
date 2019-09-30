package com.tmobile.bladerunner.exception;

import java.util.Objects;

public class BaseException extends RuntimeException {

    private final String usermessage;
    private final String systemMessage;
    private final String code;

    public BaseException() {
        this.code = "BASE_EXCEPTION";
        this.usermessage = "";
        this.systemMessage = "";
    }

    public BaseException(String code, Throwable cause) {
        super(cause);
        this.code = code;
        this.usermessage = "";
        this.systemMessage = "";
    }

    public BaseException(String code, String userMessage, Throwable cause) {
        super(userMessage, cause);
        this.code = code;
        this.usermessage = userMessage;
        this.systemMessage = cause.getLocalizedMessage();
    }

    public BaseException(String code, String systemMessage, String userMessage) {
        super(userMessage);
        this.usermessage = userMessage;
        this.code = code;
        this.systemMessage = systemMessage;
    }


    public String getUsermessage() {
        return usermessage;
    }

    public String getCode() {
        return code;
    }

    public String getSystemMessage() {
        return systemMessage;
    }


    @Override
    public int hashCode() {
        return Objects.hash(code, systemMessage, usermessage);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseException)) return false;
        BaseException that = (BaseException) o;
        return Objects.equals(getUsermessage(), that.getUsermessage()) &&
                Objects.equals(getSystemMessage(), that.getSystemMessage()) &&
                Objects.equals(getCode(), that.getCode());
    }
}
