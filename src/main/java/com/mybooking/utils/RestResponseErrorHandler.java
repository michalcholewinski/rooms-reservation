package com.mybooking.utils;

import com.mybooking.customers.CustomerAlreadyExistsException;
import com.mybooking.reservations.ReservationAlreadyExistsException;
import com.mybooking.reservations.ReservationNotFoundException;
import com.mybooking.reservations.RoomNotFoundException;
import com.mybooking.rooms.IncorrectDateRangeException;
import com.mybooking.rooms.IncorrectPriceRangeException;
import com.mybooking.users.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestResponseErrorHandler extends ResponseEntityExceptionHandler {

    public static final int CUSTOMER_ALREADY_EXISTS_ERROR_CODE = 1;
    public static final int INCORRECT_DATE_RANGE_ERROR_CODE = 2;
    public static final int INCORRECT_PRICE_RANGE_ERROR_CODE = 3;
    public static final int RESERVATION_ALREADY_EXISTS_ERROR_CODE = 4;
    public static final int ROOM_NOT_FOUND_ERROR_CODE = 5;
    public static final int USER_NOT_FOUND_ERROR_CODE = 6;
    public static final int RESERVATION_NOT_FOUND_ERROR_CODE = 7;
    public static final int VALIDATION_ERROR_CODE = 8;

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, CUSTOMER_ALREADY_EXISTS_ERROR_CODE);
    }

    @ExceptionHandler(IncorrectDateRangeException.class)
    protected ResponseEntity<Object> handleInvalidDateRequest(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, INCORRECT_DATE_RANGE_ERROR_CODE);
    }

    @ExceptionHandler(IncorrectPriceRangeException.class)
    protected ResponseEntity<Object> handleInvalidPriceRequest(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, INCORRECT_PRICE_RANGE_ERROR_CODE);
    }

    @ExceptionHandler(ReservationAlreadyExistsException.class)
    protected ResponseEntity<Object> handleReservationAlreadyExists(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, RESERVATION_ALREADY_EXISTS_ERROR_CODE);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    protected ResponseEntity<Object> handleRoomNotFound(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, ROOM_NOT_FOUND_ERROR_CODE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, USER_NOT_FOUND_ERROR_CODE);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    protected ResponseEntity<Object> handleReservationNotFound(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, RESERVATION_NOT_FOUND_ERROR_CODE);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(RuntimeException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST, VALIDATION_ERROR_CODE);
    }

    private ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request, HttpStatus status, int errorCode) {
        return handleExceptionInternal(ex, getResponseBody(ex, status, errorCode),
                new HttpHeaders(), status, request);
    }

    private ResponseDto getResponseBody(RuntimeException ex, HttpStatus httpStatus, int errorCode) {
        return new ResponseDto(ex.getMessage(), true, errorCode, httpStatus.value());
    }
}
