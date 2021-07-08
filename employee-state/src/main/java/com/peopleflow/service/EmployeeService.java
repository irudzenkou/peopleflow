package com.peopleflow.service;

import com.peopleflow.core.EmployeeDto;


public interface EmployeeService {
    EmployeeDto createNewEmployee(EmployeeDto employee);
    EmployeeDto updateEmployeeStatus(EmployeeDto dto);
}
