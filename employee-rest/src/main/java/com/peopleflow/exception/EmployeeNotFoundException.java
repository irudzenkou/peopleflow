package com.peopleflow.exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException {

    private String employeeId;

    public EmployeeNotFoundException(String employeeId) {
        this.employeeId = employeeId;
    }
}
