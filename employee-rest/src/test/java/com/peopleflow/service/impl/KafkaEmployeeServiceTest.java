package com.peopleflow.service.impl;

import com.peopleflow.exception.AddEmployeeException;
import com.peopleflow.exception.UpdateEmployeeException;
import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.dao.EmployeeRepository;
import com.peopleflow.domain.Employee;
import com.peopleflow.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class KafkaEmployeeServiceTest {

    private static final String TOPIC_MOCK = "topic";

    @MockBean
    private KafkaTemplate<String, EmployeeDto> kafkaTemplate;

    @MockBean
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    public void beforeEach() {
        employeeService = new KafkaEmployeeService(kafkaTemplate, employeeRepository, TOPIC_MOCK);
    }

    @Test
    public void addEmployeeTest() {
        EmployeeDto employee = new EmployeeDto();

        String id = employeeService.addEmployee(employee);

        assertNotNull(id);
        employee.setId(id);
        verify(kafkaTemplate).send(eq(TOPIC_MOCK), same(employee));
    }

    @Test
    public void checkTest() {
        String id = "11";

        employeeService.check(id);

        verify(kafkaTemplate).send(eq(TOPIC_MOCK), eq(getEmployeeDtoStub(id, EmployeeState.IN_CHECK)));
    }

    @Test
    public void approveTest() {
        String id = "11";

        employeeService.approve(id);

        verify(kafkaTemplate).send(eq(TOPIC_MOCK), eq(getEmployeeDtoStub(id, EmployeeState.APPROVED)));
    }

    @Test
    public void activateTest() {
        String id = "11";

        employeeService.activate(id);

        verify(kafkaTemplate).send(eq(TOPIC_MOCK), eq(getEmployeeDtoStub(id, EmployeeState.ACTIVE)));
    }

    @Test
    public void getStateTest() {
        String id = "11";
        Employee employee = new Employee();
        employee.setId(id);
        employee.setState(EmployeeState.APPROVED);
        when(employeeRepository.findById(eq(id))).thenReturn(Optional.of(employee));

        EmployeeState state = employeeService.getStateById(id);

        assertEquals(state, EmployeeState.APPROVED);
    }

    @Test
    public void addEmployeeExceptionTest() {
        when(kafkaTemplate.send(any(), any())).thenThrow(RuntimeException.class);
        assertThrows(AddEmployeeException.class, () -> employeeService.addEmployee(new EmployeeDto()));
    }

    @Test
    public void checkExceptionTest() {
        when(kafkaTemplate.send(any(), any())).thenThrow(RuntimeException.class);
        assertThrows(UpdateEmployeeException.class, () -> employeeService.check("id"));

    }

    @Test
    public void approveExceptionTest() {
        when(kafkaTemplate.send(any(), any())).thenThrow(RuntimeException.class);
        assertThrows(UpdateEmployeeException.class, () -> employeeService.approve("id"));

    }

    @Test
    public void activateExceptionTest() {
        when(kafkaTemplate.send(any(), any())).thenThrow(RuntimeException.class);
        assertThrows(UpdateEmployeeException.class, () -> employeeService.activate("id"));

    }

    private EmployeeDto getEmployeeDtoStub(String id, EmployeeState state) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        employeeDto.setState(state);
        return employeeDto;
    }
}
