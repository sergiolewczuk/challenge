package com.challenge.app.models.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(NoSuchElementException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, exc);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(DataAccessException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, exc);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(DuplicateKeyException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, exc);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(IllegalArgumentException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, exc);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(InvalidDataException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> errors = exc.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return buildResponseEntity(httpStatus, new RuntimeException("Data was invalid."), errors);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        // Aplica cuando en el URL se envia un argumento invalido, por ejemplo String
        // por Integer
        return buildResponseEntity(httpStatus, new RuntimeException("Argument type is not valid."));
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(EmptyListException exc) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return buildResponseEntity(httpStatus,exc);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(NullPointerException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, new RuntimeException("Invalid request. Use ASC or DESC to get the list order."));
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(UserNotValid exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, new RuntimeException("Invalid request. Username or Password is not valid."));
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(Exception exc) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        return buildResponseEntity(httpStatus,
                new RuntimeException("A problem occurred, report it and try again later."));
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
        return buildResponseEntity(httpStatus, exc, null);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exc,
            List<String> errors) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("" + exc.getMessage());
        error.setStatus(httpStatus.value());

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        error.setTimestamp(simpleDateFormat.format(new Date()));
        error.setErrors(errors);
        return new ResponseEntity<>(error, httpStatus);

    }

}