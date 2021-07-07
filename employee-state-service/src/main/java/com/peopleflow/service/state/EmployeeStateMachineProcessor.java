package com.peopleflow.service.state;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.state.persist.StateMachineStorage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;

import static com.peopleflow.service.state.StateMachineConstants.EMPLOYEE_OBJECT;

@Slf4j
@Setter
public class EmployeeStateMachineProcessor implements EmployeeStateProcessor {

    private StateMachineStorage stateMachineStorage;

    public EmployeeStateMachineProcessor(StateMachineStorage stateMachineStorage) {
        this.stateMachineStorage = stateMachineStorage;
    }

    @Override
    public void process(EmployeeDto employee) {
        String employeeId = employee.getId();
        StateMachine<EmployeeState, EmployeeEvent> stateMachine = stateMachineStorage.getStateMachine(employeeId);
        stateMachine.getExtendedState().getVariables().put(EMPLOYEE_OBJECT, employee);
        stateMachine.sendEvent(getEmployeeEvent(employee.getState()));
        stateMachineStorage.saveStateMachine(employeeId, stateMachine);
    }

    private EmployeeEvent getEmployeeEvent(EmployeeState state) {
        switch (state) {
            case ACTIVE:
                return EmployeeEvent.ACTIVATE;
            case APPROVED:
                return EmployeeEvent.APPROVE;
            case IN_CHECK:
                return EmployeeEvent.START_CHECK;
            default:
                return EmployeeEvent.ADD;
        }
    }
}
