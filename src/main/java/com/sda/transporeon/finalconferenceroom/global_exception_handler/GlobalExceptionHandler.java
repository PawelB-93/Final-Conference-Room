package com.sda.transporeon.finalconferenceroom.global_exception_handler;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomAlreadyExistException;
import com.sda.transporeon.finalconferenceroom.conference_room.exception.NoConferenceRoomFoundException;
import com.sda.transporeon.finalconferenceroom.organization.exception.NoOrganizationFoundException;
import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationAlreadyExistException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.NoReservationFoundException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoOrganizationFoundException.class)
    public ErrorBody handleNoOrganizationFoundException(final NoOrganizationFoundException exception) {
        log.error("No organization found!");
        return new ErrorBody(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrganizationAlreadyExistException.class)
    public ErrorBody handleOrganizationAlreadyExistException(final OrganizationAlreadyExistException exception) {
        log.error("Organization already exist!");
        return new ErrorBody(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoConferenceRoomFoundException.class)
    public ErrorBody handleNoConferenceRoomFoundException(final NoConferenceRoomFoundException exception) {
        log.error("No Conference Room found!");
        return new ErrorBody(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConferenceRoomAlreadyExistException.class)
    public ErrorBody handleConferenceRoomAlreadyExistException(final ConferenceRoomAlreadyExistException exception) {
        log.error("Conference Room already exist!");
        return new ErrorBody(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoReservationFoundException.class)
    public ErrorBody handleNoReservationFoundException(final NoReservationFoundException exception) {
        log.error("No Reservation found!");
        return new ErrorBody(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationAlreadyExistException.class)
    public ErrorBody handleReservationAlreadyExistException(final ReservationAlreadyExistException exception) {
        log.error("Reservation already exist!");
        return new ErrorBody(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorBody handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        final StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : errors) {
            errorMessage.append(fieldError.getDefaultMessage()).append(". ");
        }
        return new ErrorBody(errorMessage.toString());
    }
}
