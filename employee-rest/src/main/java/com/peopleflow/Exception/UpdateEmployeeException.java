package com.peopleflow.Exception;

import com.peopleflow.core.EmployeeState;

public class UpdateEmployeeException extends RuntimeException {

//    String id;
//    EmployeeState state;
//
//    public UpdateEmployeeException(String id , EmployeeState state, Throwable e) {
//        super(e);
//        this.id = id;
//        this.state = state;
//    }

    public UpdateEmployeeException(String id, EmployeeState state, Throwable e) {
        super(String.format("Unable to update employee state. id = {}, state = {}", id, state), e);
    }
}
