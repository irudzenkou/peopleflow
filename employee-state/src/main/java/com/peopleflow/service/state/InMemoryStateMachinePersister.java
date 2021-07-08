package com.peopleflow.service.state;

import com.peopleflow.core.EmployeeState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStateMachinePersister implements StateMachinePersist<EmployeeState, EmployeeEvent, String> {

    private final Map<String, StateMachineContext<EmployeeState, EmployeeEvent>> storage = new ConcurrentHashMap<>();

    @Override
    public void write(StateMachineContext<EmployeeState, EmployeeEvent> context, String contextObj) {
        storage.put(contextObj, context);
    }

    @Override
    public StateMachineContext<EmployeeState, EmployeeEvent> read(String contextObj) {
        return storage.get(contextObj);
    }
}
