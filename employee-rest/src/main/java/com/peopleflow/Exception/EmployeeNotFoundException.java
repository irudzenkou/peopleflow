package com.peopleflow.Exception;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException {

    private String employeeId;

    public EmployeeNotFoundException(String employeeId) {
        this.employeeId = employeeId;
    }
}
