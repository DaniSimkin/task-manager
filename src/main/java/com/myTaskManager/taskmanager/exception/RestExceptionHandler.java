package com.myTaskManager.taskmanager.exception;

import com.myTaskManager.taskmanager.exception.custom.MaxCapacityException;
import com.myTaskManager.taskmanager.exception.custom.ProcessNotExistException;
import com.myTaskManager.taskmanager.exception.custom.UnableToAddProcessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Value(value = "${process.exception.message.maximumCapacity.exception}")
    private String maximumCapacity;

    @Value(value = "${process.exception.message.processNotExist.exception}")
    private String processNotExist;

    @Value(value = "${process.exception.message.unableToAddProcess.exception}")
    private String unableToAddProcess;



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            RuntimeException ex) {
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MaxCapacityException.class)
    protected ResponseEntity<Object> handleMaxCapacityReached(
            MaxCapacityException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(maximumCapacity);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ProcessNotExistException.class)
    protected ResponseEntity<Object> handlePidNotExist(
            ProcessNotExistException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(processNotExist);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UnableToAddProcessException.class)
    protected ResponseEntity<Object> handlePidNotExist(
            UnableToAddProcessException ex) {
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR);
        apiError.setMessage(unableToAddProcess);
        return buildResponseEntity(apiError);
    }





}
