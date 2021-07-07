package com.peopleflow.service.state.action;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.EmployeeService;
import com.peopleflow.service.state.EmployeeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import static com.peopleflow.service.state.StateMachineConstants.EMPLOYEE_OBJECT;

@Slf4j
public class UpdateEmployeeStateAction implements Action<EmployeeState, EmployeeEvent> {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void execute(StateContext<EmployeeState, EmployeeEvent> stateContext) {
        EmployeeDto employeeDto = (EmployeeDto) stateContext.getStateMachine().getExtendedState().getVariables().get(EMPLOYEE_OBJECT);
        String employeeId = employeeDto.getId();
        EmployeeState state = getNewState(stateContext);
        EmployeeDto employee = new EmployeeDto();
        employee.setState(state);
        employee.setId(employeeId);
//        log.info("Update state of employee '{}' to '{}'", employeeId, state);
        employeeService.updateEmployeeStatus(employee);
    }

    protected EmployeeState getNewState(StateContext<EmployeeState, EmployeeEvent> stateContext) {
        return stateContext.getTarget().getId();
    }
}
