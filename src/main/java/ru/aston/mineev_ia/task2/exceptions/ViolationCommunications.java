package ru.aston.mineev_ia.task2.exceptions;

public class ViolationCommunications extends RuntimeException {

    public static final int STATUS_CODE = 4005;
    private final String message;

    public ViolationCommunications(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\nStatus code: " + STATUS_CODE + "\nMessage: " + message;
    }
}
