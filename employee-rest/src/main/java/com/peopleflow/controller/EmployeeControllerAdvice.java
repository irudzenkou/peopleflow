package com.peopleflow.controller;

import com.peopleflow.exception.AddEmployeeException;
import com.peopleflow.exception.EmployeeNotFoundException;
import com.peopleflow.exception.UpdateEmployeeException;
import com.peopleflow.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(AddEmployeeException.class)
    public ResponseEntity<ErrorResponse> onAddEmployeeException(AddEmployeeException e) {
        log.error(e.getMessage(), e);
        String errorMessage = ResponseErrorMessageMapper.getResponseErrorMessage(AddEmployeeException.class);
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UpdateEmployeeException.class)
    public ResponseEntity<ErrorResponse> onUpdateEmployeeException(UpdateEmployeeException e) {
        log.error("Unable to update employee state. id = {}, state = {}", e.getEmployeeId(), e.getState(), e);
        String errorMessage = ResponseErrorMessageMapper.getResponseErrorMessage(UpdateEmployeeException.class);
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> onEmployeeNotFoundException(EmployeeNotFoundException e) {
        log.error("Employee not found. id = {}", e.getEmployeeId(), e);
        String errorMessage = ResponseErrorMessageMapper.getResponseErrorMessage(EmployeeNotFoundException.class);
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> onGenericException(Exception e) {
        log.error(e.getMessage(), e);
        String errorMessage = ResponseErrorMessageMapper.getResponseErrorMessage(Exception.class);
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
