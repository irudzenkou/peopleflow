package com.peopleflow.service.state.persist;

import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.state.EmployeeEvent;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStateMachinePersister implements StateMachinePersist<EmployeeState, EmployeeEvent, String> {

    private final Map<String, StateMachineContext<EmployeeState, EmployeeEvent>> storage = new ConcurrentHashMap<>();

    @Override
    public void write(StateMachineContext<EmployeeState, EmployeeEvent> context, String contextObj) throws Exception {
        storage.put(contextObj, context);
    }

    @Override
    public StateMachineContext<EmployeeState, EmployeeEvent> read(String contextObj) throws Exception {
        return storage.get(contextObj);
    }
}
