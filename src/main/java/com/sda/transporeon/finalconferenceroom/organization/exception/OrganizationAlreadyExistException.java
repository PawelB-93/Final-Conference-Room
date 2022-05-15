package com.sda.transporeon.finalconferenceroom.organization.exception;

public class OrganizationAlreadyExistException extends IllegalArgumentException {
    public OrganizationAlreadyExistException(String s) {
        super(String.format("Organization with name: %s already exist!", s));
    }
}
