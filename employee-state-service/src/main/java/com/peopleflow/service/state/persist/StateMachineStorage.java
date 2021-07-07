package com.peopleflow.service.state.persist;

import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.state.EmployeeEvent;
import org.springframework.statemachine.StateMachine;

public interface StateMachineStorage {

    StateMachine<EmployeeState, EmployeeEvent> getStateMachine(String employeeId);

    void saveStateMachine(String employeeId, StateMachine<EmployeeState, EmployeeEvent> stateMachine);
}
