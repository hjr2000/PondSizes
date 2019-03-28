package com.qualicity;

public class PondTooLargeException extends Exception {
    PondTooLargeException(String errorMessage) {
        super(errorMessage);
    }
}
