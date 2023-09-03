package ru.aston.mineev_ia.task2.exceptions;

public class InvalidValues extends Exception {

    public static final int STATUS_CODE = 4001;
    private final String message;

    public InvalidValues(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\nStatus code: " + STATUS_CODE + "\nMessage: " + message;
    }
}

