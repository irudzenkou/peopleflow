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
public class UpdateEmployeeStateActionTest {

    @MockBean
    private EmployeeService employeeService;

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private StateContext<EmployeeState, EmployeeEvent> stateContext;

    private UpdateEmployeeStateAction action;

    @BeforeEach
    public void beforeEach() {
        action = new UpdateEmployeeStateAction();
        action.setEmployeeService(employeeService);
    }

    @Test
    public void updateEmployeeStateActionTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId("id");
        employeeDto.setState(EmployeeState.ADDED);
        when(stateContext.getStateMachine().getExtendedState().getVariables().get(EMPLOYEE_OBJECT)).thenReturn(employeeDto);
        when(stateContext.getTarget().getId()).thenReturn(EmployeeState.IN_CHECK);

        action.execute(stateContext);

        employeeDto.setState(EmployeeState.IN_CHECK);
        verify(employeeService).updateEmployeeStatus(eq(employeeDto));
    }
}
