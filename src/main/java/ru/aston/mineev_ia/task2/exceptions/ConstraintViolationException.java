package ru.aston.mineev_ia.task2.exceptions;

public class ConstraintViolationException extends RuntimeException {

    public static final int STATUS_CODE = 4005;
    private final String message;

    public ConstraintViolationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\nStatus code: " + STATUS_CODE + "\nMessage: " + message;
    }
}
