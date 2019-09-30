package com.tmobile.bladerunner.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionMapper {
    Logger logger = LoggerFactory.getLogger( ApiExceptionMapper.class);

    @ExceptionHandler({
            Exception.class
    })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Fault internalError(Exception exception) {
        logger.error("Internal Error: {} ", exception.getLocalizedMessage(), exception);
        String code = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        return new Fault(exception.getLocalizedMessage(), code);
    }

    @ExceptionHandler({
            BaseException.class
    })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    Fault commonsInternalError(BaseException exception) {
        logger.error("ErrorMessage = {} ", exception.getLocalizedMessage(), exception);
        String code = exception.getCode();
        return new Fault(exception.getLocalizedMessage(), code, exception.getSystemMessage());
    }

    @ExceptionHandler({
            MissingServletRequestParameterException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Fault missingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return logAndCreateGenericHttpFault( exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ValidationException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Fault validationException(ValidationException exception) {
        return logAndCreateGenericHttpFault( exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            com.tmobile.bladerunner.exception.ValidationException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Fault basicValidationException(com.tmobile.bladerunner.exception.ValidationException exception) {
        return logAndCreateFault(exception);
    }

    @ExceptionHandler({
            com.tmobile.bladerunner.exception.ValidationExceptionWrapper.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Fault basicValidationExceptionList(com.tmobile.bladerunner.exception.ValidationExceptionWrapper exceptions) {

        List<Fault.Error> errors = exceptions.getValidationExceptionList()
                                       .stream()
                                       .map(exception->new Fault.Error(exception.getLocalizedMessage(),exception.getCode(),exception.getSystemMessage()))
                                       .collect(Collectors.toList());
        return new Fault(errors);
    }
    @ExceptionHandler({
            EntityNotFoundException.class
    })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    Fault entityNotFoundException(EntityNotFoundException exception) {
        return logAndCreateFault(exception);
    }

    @ExceptionHandler({
            NewReferenceException.class
    })
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody
    Fault conflict(NewReferenceException exception) {
        return logAndCreateFault(exception);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Fault validationException(MethodArgumentNotValidException exception) {
        List<Fault.Error> errorErrors = exception.getBindingResult().getAllErrors()
                                                 .stream()
                                                 .map(error -> new Fault.Error(error.getDefaultMessage(), error
                                                         .getCodes()[0]))
                                                 .collect(Collectors.toList());
        Fault fault = new Fault(errorErrors);
        logger.error("ErrorMessage=\"{}\"", exception.getLocalizedMessage(), exception);
        return fault;
    }

    private Fault logAndCreateFault(BaseException baseException) {
        String systemMessage = baseException.getSystemMessage();
        String code = baseException.getCode();
        Fault fault = new Fault(baseException.getLocalizedMessage(), code, systemMessage);
        logger.error("ErrorCode=\"{}\" , ErrorMessage=\"{}\"", code, baseException
                .getLocalizedMessage(), baseException);
        return fault;
    }


    private Fault logAndCreateGenericHttpFault(Exception exception, HttpStatus httpStatus) {
        String code = httpStatus.getReasonPhrase();
        Fault fault = new Fault(exception.getLocalizedMessage(), code);
        logger.error("ErrorCode=\"{}\" , ErrorMessage=\"{}\"", code, exception.getLocalizedMessage(), exception);
        return fault;
    }

}
