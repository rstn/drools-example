package ru.peter.service.drools.api;

public class LoadClientException extends Exception{

    public LoadClientException() {
    }

    public LoadClientException(String message) {
        super(message);
    }

    public LoadClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
