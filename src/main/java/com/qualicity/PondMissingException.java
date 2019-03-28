package com.qualicity;

public class PondMissingException extends Exception {
    PondMissingException(String errorMessage) {
        super(errorMessage);
    }
}
