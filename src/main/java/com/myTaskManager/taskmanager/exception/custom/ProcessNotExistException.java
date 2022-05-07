package com.myTaskManager.taskmanager.exception.custom;

public class ProcessNotExistException extends RuntimeException {

    private String message;

    public ProcessNotExistException(String message) {
        super(message);
        this.message = message;
    }

    public ProcessNotExistException() {
    }
}
