package com.peopleflow.controller;

import com.peopleflow.Exception.AddEmployeeException;
import com.peopleflow.Exception.UpdateEmployeeException;
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
    public ResponseEntity<ErrorResponse> onNotFound(AddEmployeeException e) {
        log.error(e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse("Unable to add employee");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UpdateEmployeeException.class)
    public ResponseEntity<ErrorResponse> onNotFound(UpdateEmployeeException e) {
        log.error(e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse("Unable to update employee state");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
