package ru.mipt1c.homework.task1.exceptions;

public class MalformedDataException extends RuntimeException {
    public MalformedDataException() {
    }

    public MalformedDataException(String message) {
        super(message);
    }

    public MalformedDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedDataException(Throwable cause) {
        super(cause);
    }
}
