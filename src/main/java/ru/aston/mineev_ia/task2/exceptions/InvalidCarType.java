package ru.aston.mineev_ia.task2.exceptions;

public class InvalidCarType extends RuntimeException {

    public static final int STATUS_CODE = 4002;
    private final String message;

    public InvalidCarType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\nStatus code: " + STATUS_CODE + "\nMessage: " + message;
    }
}
