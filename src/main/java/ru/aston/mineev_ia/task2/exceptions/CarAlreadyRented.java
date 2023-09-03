package ru.aston.mineev_ia.task2.exceptions;

public class CarAlreadyRented extends RuntimeException {

    public static final int STATUS_CODE = 4003;
    private final String message;

    public CarAlreadyRented(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\nStatus code: " + STATUS_CODE + "\nMessage: " + message;
    }
}
