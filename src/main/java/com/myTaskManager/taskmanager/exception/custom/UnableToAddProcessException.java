package com.myTaskManager.taskmanager.exception.custom;

public class UnableToAddProcessException extends RuntimeException{

    private String message;

    public UnableToAddProcessException(String message) {
        super(message);
        this.message = message;
    }

    public UnableToAddProcessException() {
    }

}
