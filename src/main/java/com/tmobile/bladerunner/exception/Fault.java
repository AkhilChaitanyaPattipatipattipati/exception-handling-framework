package com.tmobile.bladerunner.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

public class Fault {
    @JsonProperty
    List<Error> errors;

    public Fault(
            @JsonProperty("errors") List<Error> errors) {

        this.errors = errors;
    }

    public Fault(String message, String code) {
        errors = new ArrayList<>(1);
        errors.add(new Error(message, code));
    }


    public Fault(String message, String code, String systemMessage) {
        errors = new ArrayList<>(1);
        errors.add(new Error(message, code, systemMessage));
    }

    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper( this)
                          .add("errors", errors)
                          .toString();
    }

    public static class Error {
        private String userMessage;
        private String systemMessage;
        private String code;

        public Error(@JsonProperty("userMessage") String userMessage, @JsonProperty("systemMessage") String systemMessage, @JsonProperty("code") String code) {
            this.userMessage = userMessage;
            this.systemMessage = systemMessage;
            this.code = code;
        }

        public Error(String defaultMessage, String s) {
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getCode() {
            return code;
        }

        public String getSystemMessage() {
            return systemMessage;
        }


        @Override
        public String toString() {
            MoreObjects.ToStringHelper moreObjects = MoreObjects.toStringHelper( this)
                                                                .add("userMessage", this.userMessage)
                                                                .add("systemMessage", this.systemMessage)
                                                                .add("code", this.code);
            return moreObjects.toString();
        }

    }
}
