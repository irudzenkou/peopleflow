package com.peopleflow.service.state.action;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.EmployeeService;
import com.peopleflow.service.state.EmployeeEvent;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import static com.peopleflow.service.state.StateMachineConstants.EMPLOYEE_OBJECT;

@Setter
public class AddEmployeeAction implements Action<EmployeeState, EmployeeEvent> {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void execute(StateContext<EmployeeState, EmployeeEvent> stateContext) {
        EmployeeDto employee = (EmployeeDto) stateContext.getStateMachine().getExtendedState().getVariables().get(EMPLOYEE_OBJECT);
        employeeService.createNewEmployee(employee);
    }
}
