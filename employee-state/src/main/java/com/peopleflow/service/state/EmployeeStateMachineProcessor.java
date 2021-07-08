package com.peopleflow.service.state;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

import static com.peopleflow.service.state.StateMachineConstants.EMPLOYEE_OBJECT;

@Slf4j
@Setter
@AllArgsConstructor
public class EmployeeStateMachineProcessor implements EmployeeStateProcessor {

    private StateMachinePersister<EmployeeState, EmployeeEvent, String> persister;

    private StateMachineFactory<EmployeeState, EmployeeEvent> stateMachineFactory;

    @Override
    public void process(EmployeeDto employee) throws Exception {
        String employeeId = employee.getId();
        StateMachine<EmployeeState, EmployeeEvent> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine = persister.restore(stateMachine, employeeId);

        stateMachine.getExtendedState().getVariables().put(EMPLOYEE_OBJECT, employee);
        EmployeeEvent event = employee.getState() == null ? EmployeeEvent.ADD : getEmployeeEventForState(employee.getState());
        stateMachine.sendEvent(event);

        persister.persist(stateMachine, employeeId);
    }

    private EmployeeEvent getEmployeeEventForState(EmployeeState state) {
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
