package com.myTaskManager.taskmanager.exception.custom;

public class MaxCapacityException extends RuntimeException{

    private String message;

    public MaxCapacityException(String message) {
        super(message);
        this.message = message;
    }

    public MaxCapacityException() {
    }
}
