package com.av.search;

public class FileReadingException extends RuntimeException {

    public FileReadingException(Throwable throwable) {
        super(throwable);
    }

    public FileReadingException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
