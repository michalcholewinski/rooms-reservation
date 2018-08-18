package com.mybooking.utils;

import com.mybooking.customers.CustomerAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestResponseErrorHandler extends ResponseEntityExceptionHandler {

    public static final int CUSTOMER_ALREADY_EXISTS_ERROR_CODE = 1;

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, CUSTOMER_ALREADY_EXISTS_ERROR_CODE);
    }

    private ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request, HttpStatus status, int errorCode) {
        return handleExceptionInternal(ex, getResponseBody(ex, status, errorCode),
                new HttpHeaders(), status, request);
    }

    private ResponseDto getResponseBody(RuntimeException ex, HttpStatus httpStatus, int errorCode) {
        return new ResponseDto(ex.getMessage(), true, errorCode, httpStatus.value());
    }
}
