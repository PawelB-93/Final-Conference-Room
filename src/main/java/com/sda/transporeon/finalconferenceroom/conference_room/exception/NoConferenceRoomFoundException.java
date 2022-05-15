package com.sda.transporeon.finalconferenceroom.conference_room.exception;

import java.util.NoSuchElementException;

public class NoConferenceRoomFoundException extends NoSuchElementException {
    public NoConferenceRoomFoundException(String s) {
        super(String.format("No Conference Room with name: %s found!", s));
    }
}
