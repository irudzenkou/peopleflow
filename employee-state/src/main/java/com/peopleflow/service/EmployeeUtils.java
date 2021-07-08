package com.peopleflow.service;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.domain.Employee;

public class EmployeeUtils {
    public static Employee convertToDomain(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAge(employeeDto.getAge());
        employee.setEmail(employeeDto.getEmail());
        employee.setSkillsDescription(employeeDto.getSkillsDescription());
        employee.setState(employeeDto.getState());
        return employee;
    }

    public static EmployeeDto convertToDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setSkillsDescription(employee.getSkillsDescription());
        employeeDto.setState(employee.getState());
        return employeeDto;
    }
}
