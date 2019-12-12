package ru.otus.hw.exception;

/**
 * Thrown out if the requested amount is zero
 */
public class ZeroSumException extends RuntimeException {

    public ZeroSumException(String message) {
        super(message);
    }
}