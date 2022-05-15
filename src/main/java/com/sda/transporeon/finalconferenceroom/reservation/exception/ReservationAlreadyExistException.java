package com.sda.transporeon.finalconferenceroom.reservation.exception;

public class ReservationAlreadyExistException extends IllegalArgumentException {
    public ReservationAlreadyExistException() {
        super("Reservation already exist!");
    }
}
