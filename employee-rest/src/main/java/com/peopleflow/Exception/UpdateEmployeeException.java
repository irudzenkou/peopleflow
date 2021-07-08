package com.peopleflow.Exception;

import com.peopleflow.core.EmployeeState;
import lombok.Getter;

@Getter
public class UpdateEmployeeException extends RuntimeException {
    String employeeId;
    EmployeeState state;

    public UpdateEmployeeException(String employeeId, EmployeeState state, Throwable e) {
        super(e);
        this.employeeId = employeeId;
        this.state = state;
    }
}
