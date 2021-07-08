package com.peopleflow.config;

import com.peopleflow.core.EmployeeState;
import com.peopleflow.dao.EmployeeRepository;
import com.peopleflow.service.EmployeeListener;
import com.peopleflow.service.EmployeeService;
import com.peopleflow.service.impl.EmployeeServiceImpl;
import com.peopleflow.service.state.EmployeeEvent;
import com.peopleflow.service.state.EmployeeStateMachineProcessor;
import com.peopleflow.service.state.EmployeeStateProcessor;
import com.peopleflow.service.state.InMemoryStateMachinePersister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public EmployeeStateProcessor stateProcessor(StateMachinePersister persister,
                                                 StateMachineFactory<EmployeeState, EmployeeEvent> stateMachineFactory) {
        return new EmployeeStateMachineProcessor(persister, stateMachineFactory);
    }

    @Bean
    public EmployeeService employeeService(EmployeeRepository employeeRepository) {
        return new EmployeeServiceImpl(employeeRepository);
    }

    @Bean
    public EmployeeListener employeeEventListener(EmployeeStateProcessor stateProcessor) {
        return new EmployeeListener(stateProcessor);
    }

    @Bean
    public RecordMessageConverter messageConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public StateMachinePersister persister() {
        return new DefaultStateMachinePersister<>(new InMemoryStateMachinePersister());
    }
}
