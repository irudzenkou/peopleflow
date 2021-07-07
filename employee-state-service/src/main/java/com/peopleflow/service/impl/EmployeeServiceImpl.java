package com.peopleflow.service.impl;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.dao.EmployeeRepository;
import com.peopleflow.domain.Employee;
import com.peopleflow.service.EmployeeService;
import com.peopleflow.service.EmployeeUtils;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Setter
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

//    @Override
//    @Transactional(readOnly = true)
//    public EmployeeDto getEmployeeById(String id) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//        Employee employee = employeeOptional.orElse(null);
//        return EmployeeUtils.convertToDto(employee);
//    }

    @Override
    @Transactional
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeUtils.convertToDomain(employeeDto);
        employee.setState(EmployeeState.ADDED);
        employee = employeeRepository.save(employee);
        return EmployeeUtils.convertToDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployeeStatus(EmployeeDto dto) {
        return null;
    }
}
