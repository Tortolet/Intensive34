package ru.aston.mineev_ia.task2.exceptions;

public class UserDoesntExist extends RuntimeException {

    public static final int STATUS_CODE = 4004;
    private final String message;

    public UserDoesntExist(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\nStatus code: " + STATUS_CODE + "\nMessage: " + message;
    }
}
