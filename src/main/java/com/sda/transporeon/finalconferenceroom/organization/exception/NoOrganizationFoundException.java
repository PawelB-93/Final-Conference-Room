package com.sda.transporeon.finalconferenceroom.organization.exception;

import java.util.NoSuchElementException;

public class NoOrganizationFoundException extends NoSuchElementException {
    public NoOrganizationFoundException(String s) {
        super(String.format("No Organization with name: %s found!", s));
    }
}
