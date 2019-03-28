package com.qualicity;

public class PondTooSmallException extends Exception {
    PondTooSmallException(String errorMessage) {
        super(errorMessage);
    }
}
