package com.spring.eduConnect.exceptions;

public class TrainerAlreadyAssignedException extends RuntimeException {
    public TrainerAlreadyAssignedException(String message) {
        super(message);
    }
}
