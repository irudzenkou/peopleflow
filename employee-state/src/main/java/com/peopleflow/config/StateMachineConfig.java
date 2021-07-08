package com.peopleflow.config;

import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.state.EmployeeEvent;
import com.peopleflow.service.state.action.AddEmployeeAction;
import com.peopleflow.service.state.action.UpdateEmployeeStateAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<EmployeeState, EmployeeEvent> {

    @Override
    public void configure(final StateMachineConfigurationConfigurer<EmployeeState, EmployeeEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<EmployeeState, EmployeeEvent> states) throws Exception {
        states
                .withStates()
                .initial(EmployeeState.NEW)
                .end(EmployeeState.ACTIVE)
                .states(EnumSet.allOf(EmployeeState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EmployeeState, EmployeeEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(EmployeeState.NEW)
                .target(EmployeeState.ADDED)
                .event(EmployeeEvent.ADD)
                .action(addAction())

                .and()
                .withExternal()
                .source(EmployeeState.ADDED)
                .target(EmployeeState.IN_CHECK)
                .event(EmployeeEvent.START_CHECK)
                .action(updateStateAction())

                .and()
                .withExternal()
                .source(EmployeeState.IN_CHECK)
                .target(EmployeeState.APPROVED)
                .event(EmployeeEvent.APPROVE)
                .action(updateStateAction())

                .and()
                .withExternal()
                .source(EmployeeState.APPROVED)
                .target(EmployeeState.ACTIVE)
                .event(EmployeeEvent.ACTIVATE)
                .action(updateStateAction());
    }

    @Bean
    public Action<EmployeeState, EmployeeEvent> addAction() {
        return new AddEmployeeAction();
    }

    @Bean
    public Action<EmployeeState, EmployeeEvent> updateStateAction() {
        return new UpdateEmployeeStateAction();
    }
}