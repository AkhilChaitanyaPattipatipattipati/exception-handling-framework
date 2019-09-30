package com.tmobile.bladerunner.exception;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ValidationExceptionWrapper extends BaseException {
    private final Set<ValidationException> validationExceptionList;
    public ValidationExceptionWrapper() {
        this.validationExceptionList = new HashSet<>(1);
    }

    public ValidationExceptionWrapper(Set<ValidationException> validationExceptionList) {
        this.validationExceptionList = validationExceptionList;
    }

    public ValidationExceptionWrapper(String code, String systemMessage, String userMessage, Set<ValidationException> validationExceptionSet) {
        super(code, systemMessage, userMessage);
        this.validationExceptionList = validationExceptionSet;
    }

    public ValidationExceptionWrapper(String code, Throwable cause, Set<ValidationException> validationExceptionList) {
        super(code, cause);
        this.validationExceptionList = validationExceptionList;
    }

    public ValidationExceptionWrapper(String code, String message, Throwable cause, Set<ValidationException> validationExceptionList) {
        super(code, message, cause);
        this.validationExceptionList = validationExceptionList;
    }

    public Set<ValidationException> getValidationExceptionList() {
        return validationExceptionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationExceptionWrapper)) return false;
        if (!super.equals(o)) return false;
        ValidationExceptionWrapper that = (ValidationExceptionWrapper) o;
        return Objects.equals(getValidationExceptionList(), that.getValidationExceptionList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getValidationExceptionList());
    }
}
