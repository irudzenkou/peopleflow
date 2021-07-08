package com.peopleflow.service.impl;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.dao.EmployeeRepository;
import com.peopleflow.domain.Employee;
import com.peopleflow.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    public void beforeEach() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void createNewEmployeeTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        String id = "id";
        employeeDto.setId(id);
        Employee employee = new Employee();
        employee.setId(id);
        employee.setState(EmployeeState.ADDED);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto response = employeeService.createNewEmployee(employeeDto);

        employeeDto.setState(EmployeeState.ADDED);
        assertEquals(employeeDto, response);
    }

    @Test
    public void updateEmployeeStatusTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        String id = "id";
        employeeDto.setId(id);
        employeeDto.setState(EmployeeState.APPROVED);
        Employee employee = new Employee();
        employee.setId(id);
        employee.setState(EmployeeState.IN_CHECK);
        when(employeeRepository.findById(eq(id))).thenReturn(Optional.of(employee));
        employee.setState(EmployeeState.APPROVED);
        when(employeeRepository.save(eq(employee))).thenReturn(employee);

        EmployeeDto response = employeeService.updateEmployeeStatus(employeeDto);

        assertEquals(employeeDto, response);
    }

    @Test
    public void updateEmployeeStatusExceptionTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        String id = "id";
        employeeDto.setId(id);
        employeeDto.setState(EmployeeState.APPROVED);

        assertThrows(IllegalArgumentException.class, () -> employeeService.updateEmployeeStatus(employeeDto));
    }
}
