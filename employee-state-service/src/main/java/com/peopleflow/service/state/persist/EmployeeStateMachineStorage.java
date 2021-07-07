package com.peopleflow.service.state.persist;

import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.state.EmployeeEvent;
import lombok.AllArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

@AllArgsConstructor
public class EmployeeStateMachineStorage implements StateMachineStorage {

    private StateMachinePersister<EmployeeState, EmployeeEvent, String> persister;

    private StateMachineFactory<EmployeeState, EmployeeEvent> stateMachineFactory;

    @Override
    public StateMachine<EmployeeState, EmployeeEvent> getStateMachine(String employeeId) {
        StateMachine<EmployeeState, EmployeeEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            return persister.restore(stateMachine, employeeId);
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void saveStateMachine(String employeeId, StateMachine<EmployeeState, EmployeeEvent> stateMachine) {
        try {
            persister.persist(stateMachine, employeeId);
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }
}
