package com.peopleflow.service;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;

public interface EmployeeService {

    String addEmployee(EmployeeDto employee);
    void check(String id);
    void approve(String id);
    void activate(String id);
    EmployeeState getStateById(String id);
}
