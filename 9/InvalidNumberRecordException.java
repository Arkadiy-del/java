package org.example;

public class InvalidNumberRecordException extends Exception {
    public InvalidNumberRecordException(String message) {
        super(message);
    }

    public InvalidNumberRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}
