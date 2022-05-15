package com.sda.transporeon.finalconferenceroom.reservation.exception;

import java.util.NoSuchElementException;

public class NoReservationFoundException extends NoSuchElementException {
    public NoReservationFoundException(String s) {
        super(String.format("No Reservation with identifier: %s found!", s));
    }
}
