package com.peopleflow.service;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.domain.Employee;

public class EmployeeUtils {
    public static Employee convertToDomain(EmployeeDto request) {
        Employee employee = new Employee();
        employee.setId(request.getId());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAge(request.getAge());
        employee.setEmail(request.getEmail());
        employee.setSkillsDescription(request.getSkillsDescription());
        employee.setState(request.getState());
        return employee;
    }

    public static EmployeeDto convertToDto(Employee request) {
        if (request == null) {
            return null;
        }
        EmployeeDto employee = new EmployeeDto();
        employee.setId(request.getId());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAge(request.getAge());
        employee.setEmail(request.getEmail());
        employee.setSkillsDescription(request.getSkillsDescription());
        employee.setState(request.getState());
        return employee;
    }
}
