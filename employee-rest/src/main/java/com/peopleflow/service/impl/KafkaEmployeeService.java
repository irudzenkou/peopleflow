package com.peopleflow.service.impl;

import com.peopleflow.Exception.AddEmployeeException;
import com.peopleflow.Exception.UpdateEmployeeException;
import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.dao.EmployeeRepository;
import com.peopleflow.domain.Employee;
import com.peopleflow.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class KafkaEmployeeService implements EmployeeService {

    private KafkaTemplate<String, EmployeeDto> kafkaTemplate;

    private EmployeeRepository employeeRepository;

    private String employeeTopic;

    @Override
    public String addEmployee(EmployeeDto employee) {
        try {
            String id = UUID.randomUUID().toString();
            employee.setId(id);
            kafkaTemplate.send(employeeTopic, employee);
            return id;
        } catch (Exception e) {
            throw new AddEmployeeException(e);
        }
    }

    @Override
    public void check(String id) {
        updateState(id, EmployeeState.IN_CHECK);
    }

    @Override
    public void approve(String id) {
        updateState(id, EmployeeState.APPROVED);
    }

    @Override
    public void activate(String id) {
        updateState(id, EmployeeState.ACTIVE);
    }

    @Override
    public EmployeeState getStateById(String id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(Employee::getState).orElse(null);
    }

    private void updateState(String id, EmployeeState state) {
        try {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(id);
            employeeDto.setState(state);
            kafkaTemplate.send(employeeTopic, employeeDto);
        } catch (Exception e) {
            throw new UpdateEmployeeException(id, state, e);
        }
    }
}
