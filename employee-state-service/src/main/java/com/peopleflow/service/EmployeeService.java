package com.peopleflow.service;

import com.peopleflow.core.EmployeeDto;


public interface EmployeeService {
//    EmployeeDto getEmployeeById(String id);
    EmployeeDto createNewEmployee(EmployeeDto employee);
    EmployeeDto updateEmployeeStatus(EmployeeDto dto);
}
