package com.peopleflow.service.state.action;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.EmployeeService;
import com.peopleflow.service.state.EmployeeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.statemachine.StateContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.peopleflow.service.state.StateMachineConstants.EMPLOYEE_OBJECT;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AddEmployeeActionTest {

    @MockBean
    private EmployeeService employeeService;

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private StateContext<EmployeeState, EmployeeEvent> stateContext;

    private AddEmployeeAction action;

    @BeforeEach
    public void beforeEach() {
        action = new AddEmployeeAction();
        action.setEmployeeService(employeeService);
    }

    @Test
    public void addEmployeeActionTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId("id");
        when(stateContext.getStateMachine().getExtendedState().getVariables().get(EMPLOYEE_OBJECT)).thenReturn(employeeDto);

        action.execute(stateContext);

        verify(employeeService).createNewEmployee(eq(employeeDto));
    }
}
