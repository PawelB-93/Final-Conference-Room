package com.sda.transporeon.finalconferenceroom.conference_room.exception;

public class ConferenceRoomAlreadyExistException extends IllegalArgumentException {
    public ConferenceRoomAlreadyExistException(String s) {
        super(String.format("Conference Room with name: %s already exist!", s));
    }
}
