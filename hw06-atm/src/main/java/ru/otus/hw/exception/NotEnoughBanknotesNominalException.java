package ru.otus.hw.exception;

/**
 * Thrown out if not enough denominations of banknotes
 */
public class NotEnoughBanknotesNominalException extends RuntimeException {

    public NotEnoughBanknotesNominalException(String message) {
        super(message);
    }
}