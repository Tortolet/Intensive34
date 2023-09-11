package ru.aston.mineev_ia.task2.exceptions;

public class OrderDoesntExist extends RuntimeException {

    public static final int STATUS_CODE = 40041;
    private final String message;

    public OrderDoesntExist(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\nStatus code: " + STATUS_CODE + "\nMessage: " + message;
    }
}
